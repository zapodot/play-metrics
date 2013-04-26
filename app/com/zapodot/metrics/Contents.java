package com.zapodot.metrics;

import com.codahale.metrics.json.MetricsModule;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import play.mvc.Content;

import java.util.concurrent.TimeUnit;

/**
 * @author sondre
 */
public class Contents {

    private static ObjectMapper mapper = new ObjectMapper().registerModule(new MetricsModule(TimeUnit.SECONDS, TimeUnit.SECONDS, true));

    public static Content toJsonContent(final Object value) {
        return new Content() {
            /**
             * The content String.
             */
            @Override
            public String body() {
                try {
                    return mapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException("Could not transform data to JSON format", e);
                }
            }

            /**
             * The default Content type to use for this content.
             */
            @Override
            public String contentType() {
                return "application/json";
            }
        };
    }
}
