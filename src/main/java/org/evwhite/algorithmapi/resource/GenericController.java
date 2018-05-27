package org.evwhite.algorithmapi.resource;

import org.evwhite.algorithmapi.response.Answer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/algorithms")
public class GenericController {

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value = "/fizzbuzz", method = GET)
    public Answer getFizzBuzz(@RequestParam(value = "limit", defaultValue = "50") int limit) {
        StringBuilder resultBuilder = new StringBuilder();

        for (int i = 1; i < limit; ++i) {
            if ((i % 3 == 0) && (i % 5 == 0)) {
                resultBuilder.append("fizzbuzz");
            } else if (i % 3 == 0) {
                resultBuilder.append("fizz");
            } else if (i % 5 == 0) {
                resultBuilder.append("buzz");
            } else {
                resultBuilder.append(i);
            }

            if (i != limit-1) {
                resultBuilder.append(" | ");
            }
        }

        return new Answer(counter.getAndIncrement(), resultBuilder.toString());
    }

}
