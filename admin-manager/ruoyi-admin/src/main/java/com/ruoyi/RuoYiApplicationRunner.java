package com.ruoyi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.ruoyi.common.utils.CacheUtils;
import com.ruoyi.system.domain.SysConfig;
import com.ruoyi.system.service.ISysConfigService;

@Component
public class RuoYiApplicationRunner implements ApplicationRunner {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ISysConfigService sysConfigService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		logger.info("***************RuoYiApplicationRunner***************");
		
		String sysUserInitPwd = (String) CacheUtils.get("sys_config","sys_config:sys.user.initPassword");
		logger.info("sysUserInitPwd:" + sysUserInitPwd);
		sysConfigService.selectConfigList(new SysConfig());
		
	}

}
