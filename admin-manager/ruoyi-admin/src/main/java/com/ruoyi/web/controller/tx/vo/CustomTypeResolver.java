package com.ruoyi.web.controller.tx.vo;

import java.util.Collection;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.impl.StdTypeResolverBuilder;

public class CustomTypeResolver extends StdTypeResolverBuilder {
    @Override
    public TypeDeserializer buildTypeDeserializer(DeserializationConfig config, JavaType baseType, Collection<NamedType> subtypes) {
        return new CustomTypeDeserializer(baseType, null, _typeProperty, _typeIdVisible, defineDefaultImpl(config, baseType));
    }
}
