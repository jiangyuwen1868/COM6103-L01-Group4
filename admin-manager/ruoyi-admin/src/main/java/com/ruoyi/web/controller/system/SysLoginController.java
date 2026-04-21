package com.ruoyi.web.controller.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.chiper.Padding;
import com.ruoyi.common.utils.chiper.SM2Util;
import com.ruoyi.common.utils.chiper.SM3Utils;
import com.ruoyi.common.utils.chiper.SM4Util;

/**
 * 登录验证
 * 
 * @author ruoyi
 */
@Controller
public class SysLoginController extends BaseController
{
    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response)
    {
        // 如果是Ajax请求，返回Json字符串。
        if (ServletUtils.isAjaxRequest(request))
        {
            return ServletUtils.renderString(response, "{\"code\":\"1\",\"msg\":\"未登录或登录超时。请重新登录\"}");
        }

        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public AjaxResult ajaxLogin(String username, String password, Boolean rememberMe)
    {
    	
    	AjaxResult result= pwdDecrypt(password);
        if((Integer) result.get("code") == 0){
            password = (String) result.get("msg");
        }else {
            return result;
        }
        
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        Subject subject = SecurityUtils.getSubject();
        try
        {
            subject.login(token);
            return success();
        }
        catch (AuthenticationException e)
        {
            String msg = "用户或密码错误";
            if (StringUtils.isNotEmpty(e.getMessage()))
            {
                msg = e.getMessage();
            }
            return error(msg);
        }
    }

    @GetMapping("/unauth")
    public String unauth()
    {
        return "error/unauth";
    }
    
    private static AjaxResult pwdDecrypt(String password)
    {
    	 String sm2privateKey = "B1FF6EA5D988919F78167316B80E39B170AE6A0F250AE0CA1B9D24906CC99788";
        try {
            JSONObject obj = JSONObject.parseObject(password);
            String zekEnvelope = obj.getString("zekEnvelope");
            String zakEnvelope = obj.getString("zakEnvelope");
            String hmac = obj.getString("hmac");
            String cipherData = obj.getString("cipherData");
            // 1.1 解密zek
            byte[] zekByte = SM2Util.decryptC1C3C2(sm2privateKey, "04" + zekEnvelope);
            String zek = new String(zekByte);
            //1.2 解密zak
            byte[] zakByte = SM2Util.decryptC1C3C2(sm2privateKey, "04" + zakEnvelope);
            String zak = new String(zakByte);
            // 2、使用zek解密密文数据
            SM4Util sm4 = new SM4Util();
            sm4.hexString = true;
            sm4.secretKey = zek;
            sm4.padding = Padding.PADDING;
            byte[] plainDataBytes = sm4.decryptDataHex_ECB(cipherData);
            password = new String(plainDataBytes);
            //logger.debug("password:{}", password);
            // 3、使用zak计算明文数据mac
            String _hmac = SM3Utils.hmac(zak, password);
            //logger.debug("hmac:{}", _hmac);
            // 4、比较hmac是否一致
            if (!hmac.equalsIgnoreCase(_hmac)) {
                AjaxResult.error("安全校验验证失败！请联系管理员！");
            }
            //password = RsaUtils.decryptByPrivateKey(password);  //rsa算法解密密码
        } catch (Exception e) {
            return AjaxResult.error("安全认证失败！请联系管理员！");
        }
        return AjaxResult.success(password);
    }
}
