package vn.chohoa.flower.dto.jsonPares;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomLocalDateTimeSerializer extends StdSerializer<LocalDateTime> {

    public CustomLocalDateTimeSerializer() {
        super(LocalDateTime.class);
    }

    public CustomLocalDateTimeSerializer(Class<LocalDateTime> t) {
        super(t);
    }

    @Override
    public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (localDateTime != null) {
            DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH/mm");
            jsonGenerator.writeString(localDateTime.format(FORMATTER));
        }
    }
}
