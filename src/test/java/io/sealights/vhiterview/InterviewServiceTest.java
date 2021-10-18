package io.sealights.vhiterview;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InterviewServiceTest {

    private IInterviewService interviewService;
    public static final String QUEUE_NAME = "test";

    @BeforeEach
    public void setUp() {
        interviewService = new InterviewService();
    }

    @Test
    void putAndRetrieveTest() {
        String expectedResult = "test-payload";
        interviewService.enqueue(QUEUE_NAME, expectedResult);
        String actualResult = interviewService.dequeue(QUEUE_NAME);
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void checkEmptyQueueTest() {
        String actualResult = interviewService.dequeue(QUEUE_NAME);
        String expectedResult = "";
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getSnapshotTest() {
        List<String> expectedResults = new ArrayList<>();
        expectedResults.add("elem1");
        expectedResults.add("elem2");
        interviewService.enqueue(QUEUE_NAME, "elem1");
        interviewService.enqueue(QUEUE_NAME, "elem2");
        List<String> actualResult = interviewService.getSnapshot(QUEUE_NAME);
        assertEquals(expectedResults, actualResult);
    }

    @Test
    void enqueue() {
        interviewService.enqueue(QUEUE_NAME, "test-payload");
    }

    @Test
    void enqueueOverload() {
        interviewService.enqueue(QUEUE_NAME, "test-payload");
    }
}
