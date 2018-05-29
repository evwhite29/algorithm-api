package org.evwhite.algorithmapi;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class CoordinateDeserializer extends JsonDeserializer<Coordinate> {
    @Override
    public Coordinate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
        int row = jsonNode.get("row").asInt();
        int column = jsonNode.get("column").asInt();
        return new Coordinate(row, column);
    }
}
