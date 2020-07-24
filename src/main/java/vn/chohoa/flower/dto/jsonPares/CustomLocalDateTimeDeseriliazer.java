package vn.chohoa.flower.dto.jsonPares;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomLocalDateTimeDeseriliazer extends StdDeserializer<LocalDateTime> {

    public CustomLocalDateTimeDeseriliazer(){
        super(LocalDateTime.class);
    }
    public CustomLocalDateTimeDeseriliazer(Class<LocalDateTime> t){
        super(t);
    }

    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        if (StringUtils.hasText(jsonParser.getValueAsString())){

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return  LocalDateTime.parse(jsonParser.getValueAsString());
        }
        return  null;
    }
}
