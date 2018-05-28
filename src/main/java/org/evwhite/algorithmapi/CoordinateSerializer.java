package org.evwhite.algorithmapi;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class CoordinateSerializer extends JsonSerializer<Coordinate> {
    @Override
    public void serialize(Coordinate coordinate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("horizontal", coordinate.getHorizontal());
        jsonGenerator.writeNumberField("vertical", coordinate.getVertical());
        jsonGenerator.writeEndObject();
    }
}
