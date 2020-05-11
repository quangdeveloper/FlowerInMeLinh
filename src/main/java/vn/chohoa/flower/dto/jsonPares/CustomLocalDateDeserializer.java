package vn.chohoa.flower.dto.jsonPares;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomLocalDateDeserializer extends StdDeserializer<LocalDate> {

    protected CustomLocalDateDeserializer(Class<?> vc) {
        super(vc);
    }
    protected CustomLocalDateDeserializer() {
        this(null);
    }

    @Override
    public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

        if(StringUtils.hasText(jsonParser.getValueAsString())){
            DateTimeFormatter DATE_FORMATTER
                    = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            return LocalDate.parse(jsonParser.getValueAsString(),DATE_FORMATTER);
        }
        return  null;

    }
}
