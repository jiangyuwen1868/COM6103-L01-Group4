package com.ruoyi.web.controller.tx.vo;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.impl.AsPropertyTypeDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TreeTraversingParser;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class CustomTypeDeserializer extends AsPropertyTypeDeserializer {
    private static final long serialVersionUID = 1L;

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    public CustomTypeDeserializer(JavaType bt, TypeIdResolver idRes, String typePropertyName, boolean typeIdVisible, JavaType defaultImpl) {
        super(bt, idRes, typePropertyName, typeIdVisible, defaultImpl);
    }

    public CustomTypeDeserializer(AsPropertyTypeDeserializer src, BeanProperty property) {
        super(src, property);
    }

    @Override
    public TypeDeserializer forProperty(BeanProperty prop) {
        return (prop == _property) ? this : new CustomTypeDeserializer(this, prop);
    }

    @Override
    public Object deserializeTypedFromObject(JsonParser p, DeserializationContext ctxt) throws IOException {
        logger.debug("==========CustomTypeDeserializer->deserializeTypedFromObject");

        JsonNode node = p.readValueAsTree();
        JsonNode txHeaderNode = node.path("tx_header");
        if (!txHeaderNode.isMissingNode()) {
            JsonNode sysTxCodeNode = txHeaderNode.path("sys_tx_code");
            if (!sysTxCodeNode.isMissingNode()) {
                JsonNode txBodyNode = node.path("tx_body");
                if (!txBodyNode.isMissingNode()) {
                    ((ObjectNode) txBodyNode).put("sys_tx_code", sysTxCodeNode.asText(""));
                }
            }
        }
        logger.debug("{}", node);

        JavaType type = TypeFactory.defaultInstance().constructType(TxRequestInfo.class);

        JsonParser jsonParser = new TreeTraversingParser(node);
        if (jsonParser.getCurrentToken() == null) {
            jsonParser.nextToken();
        }

        JsonDeserializer<Object> deser = ctxt.findContextualValueDeserializer(type, _property);
        return deser.deserialize(jsonParser, ctxt);
    }
}
