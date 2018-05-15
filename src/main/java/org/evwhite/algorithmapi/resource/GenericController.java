package org.evwhite.algorithmapi.resource;

import org.evwhite.algorithmapi.Answer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/algorithms")
public class GenericController {

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value = "/testalg", method = GET)
    public Answer getTest(@RequestParam(value = "fizzOrBuzz", defaultValue = "Fizz") String fizzOrBuzz) {
        return new Answer(counter.getAndIncrement(), fizzOrBuzz);
    }

}
