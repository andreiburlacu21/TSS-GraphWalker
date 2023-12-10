package org.example.authapi.test_automation.graphwalker.api;

import lombok.extern.slf4j.Slf4j;

import org.graphwalker.java.test.Result;
import org.graphwalker.java.test.TestExecutor;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;

@Slf4j
class Runner {
    @Test
    void runTests() throws IOException {
        TestExecutor executor = new TestExecutor(MySystemTests.class);

        // execute the tests
        Result result = executor.execute(true);

        System.out.println(result.getResultsAsString());


        for (var error : result.getErrors()) {
            System.out.println(error);
        }

        // assert that there are no errors in the test execution
        assertFalse(result.hasErrors(), "There are errors in the tests");
    }
}
