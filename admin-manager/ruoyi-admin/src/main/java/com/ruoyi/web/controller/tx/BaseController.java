package com.ruoyi.web.controller.tx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.system.exception.MessageParseException;
import com.ruoyi.web.controller.tx.vo.TxRequestBodyEntity;
import com.ruoyi.web.controller.tx.vo.TxRequestInfo;

public class BaseController {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private final ObjectMapper objectMapper = new ObjectMapper();
    
    public static boolean RedisActive = true;//redis是否可用标识

    public String serialize(Object obj) {
        String json = "";

        try {
            json = objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new MessageParseException(e.getMessage(), e);
        }

        return json;
    }

    public <E extends TxRequestBodyEntity> TxRequestInfo<E> deserialize(Class<E> clazz, String json) {
        TxRequestInfo<E> txRequestInfo = null;

        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(TxRequestInfo.class, clazz);
        try {
            txRequestInfo = objectMapper.readValue(json, javaType);
        } catch (JsonMappingException e) {
            throw new MessageParseException(e.getMessage(), e);
        } catch (JsonProcessingException e) {
            throw new MessageParseException(e.getMessage(), e);
        }

        return txRequestInfo;
    }
}
