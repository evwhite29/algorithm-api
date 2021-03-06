package org.evwhite.algorithmapi.resource;

import org.evwhite.algorithmapi.Coordinate;
import org.evwhite.algorithmapi.GameConfig;
import org.evwhite.algorithmapi.response.Answer;
import org.evwhite.algorithmapi.service.CGOLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/algorithms")
public class GenericController {

    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private CGOLService cgolService;

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

    @RequestMapping(value = "/gameoflife", method = POST)
    public List<Coordinate> playLife(@RequestBody GameConfig gameConfig) {
        return cgolService.playGameOfLife(gameConfig);
    }
}
