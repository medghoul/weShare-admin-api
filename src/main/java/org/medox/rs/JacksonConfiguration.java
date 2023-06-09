package org.medox.rs;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Provider
public class JacksonConfiguration implements ContextResolver<ObjectMapper> {
    @Override
    public ObjectMapper getContext(Class<?> aClass) {
        ObjectMapper mapper = new ObjectMapper();

        SimpleModule module = new SimpleModule("CustomDate");
        module.addSerializer(LocalDate.class,
                new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        module.addDeserializer(LocalDate.class,
                new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        mapper.registerModule(module);

        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);

        return mapper;
    }
}
