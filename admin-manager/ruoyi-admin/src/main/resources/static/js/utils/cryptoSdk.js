var randomtext = "";
//公钥必须以04开头
var g_sm2pubKey ="0412450BC59C0C7A50A41177DBD13A12BF6A18052310B71BBAB503A6A501405ADDAD5EF82650F71BD8E6DA3DC1A1A7F4CFF35E4458B47C41FAA4221E67DF767A66";
//var g_zekkey = "";
//var g_zakkey = "";
var g_keyTermidTimeArray = [];
var g_timeout = 30*60*1000; //密钥存储时间 30分钟
var g_randomLen = 32;      
var g_localkey = "2342A740CBFC098C2434E6101DD88EA0"; //本地加解密使用的密钥

var sm2CipherModel = {
 C1C2C3 : 0,
 C1C3C2 : 1
};

function stringToByte(hexStr) {
	var result = new Array(hexStr.length /2);
	for (let i = 0; i < hexStr.length / 2; i++) {
		let high = parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
		let low = parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
		result[i] = (high * 16 + low);
	}
	return result;
}

/**
 * utf8 串转字节数组
 */
function utf8ToArray(str) {
  const arr = []

  for (let i = 0, len = str.length; i < len; i++) {
    const point = str.codePointAt(i)

    if (point <= 0x007f) {
      // 单字节，标量值：00000000 00000000 0zzzzzzz
      arr.push(point)
    } else if (point <= 0x07ff) {
      // 双字节，标量值：00000000 00000yyy yyzzzzzz
      arr.push(0xc0 | (point >>> 6)) // 110yyyyy（0xc0-0xdf）
      arr.push(0x80 | (point & 0x3f)) // 10zzzzzz（0x80-0xbf）
    } else if (point <= 0xD7FF || (point >= 0xE000 && point <= 0xFFFF)) {
      // 三字节：标量值：00000000 xxxxyyyy yyzzzzzz
      arr.push(0xe0 | (point >>> 12)) // 1110xxxx（0xe0-0xef）
      arr.push(0x80 | ((point >>> 6) & 0x3f)) // 10yyyyyy（0x80-0xbf）
      arr.push(0x80 | (point & 0x3f)) // 10zzzzzz（0x80-0xbf）
    } else if (point >= 0x010000 && point <= 0x10FFFF) {
      // 四字节：标量值：000wwwxx xxxxyyyy yyzzzzzz
      i++
      arr.push((0xf0 | (point >>> 18) & 0x1c)) // 11110www（0xf0-0xf7）
      arr.push((0x80 | ((point >>> 12) & 0x3f))) // 10xxxxxx（0x80-0xbf）
      arr.push((0x80 | ((point >>> 6) & 0x3f))) // 10yyyyyy（0x80-0xbf）
      arr.push((0x80 | (point & 0x3f))) // 10zzzzzz（0x80-0xbf）
    } else {
      // 五、六字节，暂时不支持
      arr.push(point)
      throw new Error('input is not supported')
    }
  }

  return arr
}

/*
ES5
*/
function ArrayToHex(arr) {
    var ret = ''
    for (var i=0;i<arr.length;i++)
    { 
        var item = arr[i].toString(16)
        item = item.length === 1 ? '0' + item : item
        ret += item
    }
    return ret
}

function getTimeSpace(){
	var d = new Date();
	return d.getTime();
}

function getGuid(num) {
	var guid = "";
	var prefix11 = ""; //基于时间产生的随机数
	var x = Math.random(); //获取一个0~1之间的随机数
	var y = getTimeSpace(); //返回 1970 年 1 月 1 日至今的毫秒数

	if (x < 0.1) x += 0.1;

	//根据当前日期，产生GUID的前11位，避免GUID重复，引入一个随机数
	prefix11 = Math.round(x * y * 10).toString(16);
	guid += prefix11.substring(0, 8);
	guid += "";
	guid += prefix11.substring(8, 11);

	//后面的数据位，完全采用随机数产生
 	for (var i = 12; i <= 32; i++) {
		var g = Math.floor(Math.random() * 16).toString(16);
		guid += g;
		if (i == 12 || i == 16 || i == 20) {
			guid += "";
		}
	}
	return guid.substr(0,num).toUpperCase();
}

function createErrJson(code,msg){
	var jsonText = {
		"code":code,
		"msg": msg
	};
	return JSON.stringify(jsonText);
}

function createKeyMapArray(keyIdData,zekData,zakData,timespaceData){

	const keyId = { "keyId": keyIdData };
	const zak = { "zak": zakData };
	const zek = { "zek": zekData };
	const timespace = { "timespace": timespaceData };

	return Object.assign(keyId, zak, zek,timespace);
}

function joinArray(dataArray,data2Array){
	dataArray.push(data2Array);
}

function removeArray(dataArray,keyId){
	for (var i = 0; i < dataArray.length; i++) { 
        if (dataArray[i].keyId == keyId)  {
			dataArray.splice(i, 1)
       }
    }
}

function getvalueFromeKey(dataArray,keyId,key){
	var value = "";
	for (var i = 0; i < dataArray.length; i++) { 
        if (dataArray[i].keyId == keyId)  {
			//获取obj数据对象
			var objTmp = dataArray[i];
			//从obj中获取
			value = objTmp[key];
		}
       }
	return value;
}

/*
设置公钥
*/
function setWorkKey(inPubKey){
	if (inPubKey === "")			
		return createErrJson(-1,"PubKey null");
	
	if("04" != inPubKey.substr(0,2))
		g_sm2pubKey = "04" + inPubKey;
	else 
		g_sm2pubKey = inPubKey;
	return createErrJson(0,"success");
}

function getSm3Hmac(inputdata, inputkey) {
	key = stringToByte(inputkey)
	input =  utf8ToArray(inputdata)
	var sm3hmc = hmac(input, key);
    return ArrayToHex(sm3hmc).toUpperCase();
}

/*
* 对数据进行加密
*/
function tranEncrypt(data){
	
	try{
		
		if (g_sm2pubKey === "")		return createErrJson(-1,"sm2pubKey null");
		if (data === "")			return createErrJson(-1,"data null");
	
		var zekkey 	  = getGuid(g_randomLen); 
		var zakkey 	  = getGuid(g_randomLen); 
		var keyId    = getGuid(g_randomLen); 
		var timespace = getTimeSpace(); 
	
		var cipherData  = sm4.encrypt(data, zekkey).toUpperCase();
		var sm3hmactext = getSm3Hmac(data,zakkey);
	
		joinArray(g_keyTermidTimeArray,
			  createKeyMapArray(keyId,zekkey,zakkey,timespace));
								   
		var zekCipherSm2   = "";
		var zakCipherSm2   = "";
	
		zekCipherSm2 = sm2.doEncrypt(zekkey, g_sm2pubKey, sm2CipherModel.C1C3C2).toUpperCase();
		zakCipherSm2 = sm2.doEncrypt(zakkey, g_sm2pubKey, sm2CipherModel.C1C3C2).toUpperCase();
	
		var jsonText = {
			"code":0,
			"msg":"success",
			"data":{
				"zekEnvelope":zekCipherSm2,
				"zakEnvelope":zakCipherSm2,
				"hmac":sm3hmactext,
				"cipherData":cipherData,
				"keyId":keyId //惟一标识符
			}
		};
		return JSON.stringify(jsonText);
	}
	catch(error){
		return createErrJson(-99,error.toString());
	}
}


function tranDecrypt(cipherText,hmacText,keyId){
	try{
		
		if (keyId === "")			return createErrJson(-1,"keyId null");
		if (cipherText === "")			return createErrJson(-1,"dataEnc null");
		if (hmacText === "")			return createErrJson(-1,"hmac null");
		if (g_keyTermidTimeArray.length <= 0) return createErrJson(-1,"key null");
		
		/*
		for(var i =0;i<g_keyTermidTimeArray.length;i++){
			for(var j in g_keyTermidTimeArray[i]){
				console.log(j+":"+g_keyTermidTimeArray[i][j])
			}
		}
		*/
		
		//1.先判读有没有这个密钥
		var keyIdFromArray = getvalueFromeKey(g_keyTermidTimeArray,keyId,"keyId")
		if(keyIdFromArray === ""){
			var jsonText = {
				"code":-1,
				"msg":"密钥己失效",
				"data":""
			};
			return JSON.stringify(jsonText);
		}
		
		var zekkey = getvalueFromeKey(g_keyTermidTimeArray,keyId,"zek");
		var zakkey = getvalueFromeKey(g_keyTermidTimeArray,keyId,"zak");
		
		console.log("zek=" + zekkey);
		console.log("zak=" + zakkey);
		
		if(zekkey === "" ||  zakkey === ""){
			var jsonText = {
				"code":-1,
				"msg":"密钥不存在",
				"data":""
			};
			return JSON.stringify(jsonText);
		}
		
		//判断时间是否过期
		var timespaceData =  getvalueFromeKey(g_keyTermidTimeArray,keyId,"timespace");
		var timespaceNow = getTimeSpace(); 
		console.log(timespaceNow - timespaceData );
		if(timespaceNow - timespaceData >= g_timeout){
			console.log("己过期");
			removeArray(g_keyTermidTimeArray,keyId);
		}
		else {
			//console.log("未过期");
		}
		
		var plaintText 	= sm4.decrypt(cipherText,zekkey);
		var sm3hmactext = getSm3Hmac(plaintText,zakkey);
		if(hmacText.toString().toUpperCase() === sm3hmactext.toString())
		{
			var jsonText = {
				"code":0,
				"msg":"success",
				"data":plaintText
			};
			return JSON.stringify(jsonText);
		}
		else 
		{
			var jsonText = {
				"code":-1,
				"msg":"HMAC验证失败",
				"data":""
			};
			return JSON.stringify(jsonText);
		}
	}
	catch(error){
		return createErrJson(-99,error.toString());
	}
}

function pinEncrypt(data){
	try{
		
		if (g_sm2pubKey === "")		return createErrJson(-1,"sm2pubKey null");
		if (data === "")			return createErrJson(-1,"data null");
	
		var zekkey 	  = getGuid(g_randomLen);
		var zakkey 	  = getGuid(g_randomLen);
		var keyId    =  getGuid(g_randomLen); 
	
		var cipherData  = sm4.encrypt(data, zekkey).toUpperCase();
		var sm3hmactext = getSm3Hmac(data,zakkey);
	
		var zekCipherSm2   = "";
		var zakCipherSm2   = "";
	
		zekCipherSm2 = sm2.doEncrypt(zekkey, g_sm2pubKey, sm2CipherModel.C1C3C2).toUpperCase();
		zakCipherSm2 = sm2.doEncrypt(zakkey, g_sm2pubKey, sm2CipherModel.C1C3C2).toUpperCase();
	
		var jsonText = {
			"code":0,
			"msg":"success",
			"data":{
				"zekEnvelope":zekCipherSm2,
				"zakEnvelope":zakCipherSm2,
				"hmac":sm3hmactext,
				"cipherData":cipherData
			}
		};
		return JSON.stringify(jsonText);
	}
	catch(error){
		return createErrJson(-99,error.toString());
	}
}


function localEncrypt(data){
	try{
		if(g_localkey === "" || data === ""){
			return createErrJson(-1,"localkey or data null");
		}
		var cipherData  = sm4.encrypt(data, g_localkey).toUpperCase();
		var timespace = getTimeSpace(); 
		var keyId    = getGuid(g_randomLen); 
	
		joinArray(g_keyTermidTimeArray,
			  createKeyMapArray(keyId,"","",timespace));
		var jsonText = {
			"code":0,
			"msg":"success",
			"data":{
				"cipherData":cipherData
			}
		};
		return JSON.stringify(jsonText);
	}
	catch(error){
		return createErrJson(-99,error.toString());
	}
}

function localDecrypt(cipherText){
	try{
		if(g_localkey === "" || cipherText === ""){
			return createErrJson(-1,"localkey or data null");
		}
		var plaintText 	= sm4.decrypt(cipherText,g_localkey);
		var jsonText = {
				"code":0,
				"msg":"success",
				"data":plaintText
		};
		return JSON.stringify(jsonText);
	}
	catch(error){
		return createErrJson(-99,error.toString());
	}
}