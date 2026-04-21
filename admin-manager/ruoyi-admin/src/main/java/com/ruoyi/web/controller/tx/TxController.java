package com.ruoyi.web.controller.tx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.system.aop.ManagerAuthAction;

@RestController
public class TxController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "/manager/txx", consumes = "application/json", produces = "application/json; charset=utf-8")
	@ManagerAuthAction
	public JSONObject txServices(@RequestBody JSONObject message) {
		
		
		return message;
	}
	
}
