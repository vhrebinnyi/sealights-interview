package io.sealights.vhiterview;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class InterviewService implements IInterviewService {

    public static final int CAPACITY = 100;
    private Map<String, Queue> queueMap = new ConcurrentHashMap<>();

    @Override
    public String dequeue(String queueName) {
        Queue<String> queue = getQueueByName(queueName);
        String payload = queue.poll();
        if (Objects.isNull(payload)) {
            return "";
        }
        return payload;
    }

    @Override
    public void enqueue(String queueName, String payload) {
        Queue<String> queue = getQueueByName(queueName);
        queue.add(payload);
    }

    @Override
    public List<String> getSnapshot(String queueName) {
        Queue<String> queue = getQueueByName(queueName);
        List<String> result = queue.stream().collect(Collectors.toList());
        return result;
    }

    //FIXME vhrebinnyi
    private Queue<String> getQueueByName(String queueName) {
        Queue<String> queue;
        if (queueMap.containsKey(queueName)) {
            queue = queueMap.get(queueName);
        } else {
            queue = new ArrayBlockingQueue(CAPACITY);
        }
        queueMap.put(queueName, queue);
        return queueMap.get(queueName);
    }
}
