//package org.example.config;
//
//
//import com.fasterxml.jackson.core.JsonGenerator;
//import com.fasterxml.jackson.core.JsonToken;
//import com.fasterxml.jackson.core.type.WritableTypeId;
//import com.fasterxml.jackson.databind.JavaType;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.SerializerProvider;
//import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
//import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
//import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
//import com.fasterxml.jackson.databind.ser.std.StdSerializer;
//
//import java.io.IOException;
//import java.lang.reflect.Type;
//
//@JacksonStdImpl
//public class ToStringSerializer extends StdSerializer<Object> {
//    public static final com.fasterxml.jackson.databind.ser.std.ToStringSerializer instance = new com.fasterxml.jackson.databind.ser.std.ToStringSerializer();
//
//    public ToStringSerializer() {
//        super(Object.class);
//    }
//
//    public ToStringSerializer(Class<?> handledType) {
//        super(handledType, false);
//    }
//
//    public boolean isEmpty(SerializerProvider prov, Object value) {
//        return value.toString().isEmpty();
//    }
//
//    public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
//        gen.writeString(value.toString());
//    }
//
//    public void serializeWithType(Object value, JsonGenerator g, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
//        WritableTypeId typeIdDef = typeSer.writeTypePrefix(g, typeSer.typeId(value, JsonToken.VALUE_STRING));
//        this.serialize(value, g, provider);
//        typeSer.writeTypeSuffix(g, typeIdDef);
//    }
//
//    public JsonNode getSchema(SerializerProvider provider, Type typeHint) throws JsonMappingException {
//        return this.createSchemaNode("string", true);
//    }
//
//    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
//        this.visitStringFormat(visitor, typeHint);
//    }
//}
