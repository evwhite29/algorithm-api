package org.evwhite.algorithmapi.resource;

import org.evwhite.algorithmapi.response.Answer;
import org.junit.Test;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class GenericControllerTest {

    private GenericController subject = new GenericController();

    @Test
    public void getFizzBuzzReturnsAnswerObject() {
        int defaultLimit = 50;

        Object response = subject.getFizzBuzz(defaultLimit);

        assertThat(response, instanceOf(Answer.class));
    }

    @Test
    public void getFizzBuzzAnswerContainsAccurateResultString() {
        int specifiedLimit = 20;
        String expectedStringResult = "1 | 2 | fizz | 4 | buzz | fizz | 7 | 8 | fizz | buzz | 11 |" +
                " fizz | 13 | 14 | fizzbuzz | 16 | 17 | fizz | 19";

        Answer response = subject.getFizzBuzz(specifiedLimit);

        String actualStringResult = response.getStringResult();
        assertThat(actualStringResult, is(expectedStringResult));
    }
}