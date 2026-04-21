package com.jyw.csp.api.contant;

public class CspContants {

	public final static String SYS_PKG_VERSION = "sys_pkg_version";
	public final static String SYS_PKG_VERSION_01 = "01";
	public final static String SYS_REQ_TIME = "sys_req_time";
	public final static String SYS_EVT_TRACE_ID = "sys_evt_trace_id";
	public final static String SYS_SND_SERIAL_NO = "sys_snd_serial_no";
	public final static String SYS_SND_SERIAL_NO_1 = "1";
	public final static String SYS_TX_CODE = "sys_tx_code";
	public final static String SYS_PKG_STS_TYPE = "sys_pkg_sts_type";
	public final static String SYS_PKG_STS_TYPE_00 = "00";
	public final static String TX_HEADER = "tx_header";
	public final static String ENTITY = "entity";
	public final static String ENTITY_PARAMS = "params";
	public final static String APPID = "appId";
	public final static String TENANTID = "tenantId";
	public final static String CHANNELTXCODE = "channelTxCode";
	public final static String CHANNELTXCODE_DEFUALT = "CspOpenApi";
	public final static String SEC_VERSION_01 = "01";
	public final static String SEC_VERSION_02 = "02";
	public final static String COM1 = "com1";
	public final static String TX_BODY = "tx_body";
	public final static String SHA1 = "SHA1";
	public final static String TRACEID = "traceId";
	public final static String SRVCOSTTIME = "srvCostTime";
	public final static String SUCCESS_CODE = "000000000000";
	public final static String SYS_RESP_CODE = "sys_resp_code";
	public final static String SYS_RESP_DESC = "sys_resp_desc";
	public final static String HSMRSPDATA = "hsmrspdata";
	public final static String ERRCODE = "errcode";
	public final static String SUCCESS_HSM_CODE = "00";
	
	public final static long FILE_MAX_LENGTH = 1024 * 1024 * 10L;
	
	/*
	 * 加密机指令管理交易码列表
	 */
	public final static String CSP_HSMCMD_MANAGER_TXCODES = "CSP000000,CSP000001";

	/*
	 * 数字信封加解密交易码
	 */
	public final static String BIG_MSG_TXCODES = "CSPSX1001,CSPSX1002";
	/*
	 * 时间戳交易码列表
	 */
	public final static String CSP_TS_TXCODES = "CSPTS1001,CSPTS1002,CSPTS1003,CSPTS1004,CSPTS1005,CSPTS1006,CSPTS1007";
	/*
	 *签名验签服务器交易码列表前缀 
	 */
	public final static String CSP_SVS_TXCODES_PREFIX = "CSPSVS";
}
