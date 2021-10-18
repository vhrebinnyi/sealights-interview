package io.sealights.vhiterview;

import java.util.List;

public interface IInterviewService {

    String dequeue(String queueName);

    void enqueue(String queueName, String payload);

    List<String> getSnapshot(String queueName);
}
