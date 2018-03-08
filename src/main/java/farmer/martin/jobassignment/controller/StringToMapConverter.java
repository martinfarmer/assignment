package farmer.martin.jobassignment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Converter that will convert the json string containing the metadata to a Map<String, String>
 * @author martinfarmer
 */
@Component
public class StringToMapConverter implements Converter<String, Map<String, String>> {

    @Override
    public Map<String, String> convert(String source) {
        try {
            return (HashMap<String, String>) new ObjectMapper().readValue(source, HashMap.class);
        } catch (IOException ex) {
            return new HashMap<>();
        }
    }
}
