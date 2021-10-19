package io.sealights.vhiterview;

import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exercise")
@Log
public class InterviewController {

    private IInterviewService interviewService;

    public InterviewController(InterviewService interviewService) {
        log.info("init InterviewController");
        this.interviewService = interviewService;
    }

    @PostMapping("/{queueName}")
    public void enqueue(@PathVariable("queueName") String queueName, @RequestBody String payload) {
        interviewService.enqueue(queueName, payload);
    }

    @GetMapping("/{queueName}")
    public String dequeue(@PathVariable("queueName") String queueName) {
        return interviewService.dequeue(queueName);
    }

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
}
