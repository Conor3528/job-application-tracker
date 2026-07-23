package com.conorryan.jobtracker;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

import java.util.List;

@RestController
@RequestMapping("/api/applications/{applicationId}/interviews")
public class InterviewController {

    private final InterviewRepository interviewRepository;
    private final ApplicationRepository applicationRepository;

    public InterviewController(InterviewRepository interviewRepository,
                               ApplicationRepository applicationRepository) {
        this.interviewRepository = interviewRepository;
        this.applicationRepository = applicationRepository;
    }

    @GetMapping
    public ResponseEntity<List<Interview>> getInterviews(@PathVariable Long applicationId) {
        if (!applicationRepository.existsById(applicationId)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(interviewRepository.findByApplicationId(applicationId));
    }

    @PostMapping
    public ResponseEntity<Interview> createInterview(@PathVariable Long applicationId,
                                                     @Valid @RequestBody Interview interview) {
        return applicationRepository.findById(applicationId)
                .map(application -> {
                    interview.setApplication(application);
                    return ResponseEntity.ok(interviewRepository.save(interview));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{interviewId}")
    public ResponseEntity<Void> deleteInterview(@PathVariable Long applicationId,
                                                @PathVariable Long interviewId) {
        Optional<Interview> interview = interviewRepository.findById(interviewId);

        if (interview.isEmpty() || !interview.get().getApplication().getId().equals(applicationId)) {
            return ResponseEntity.notFound().build();
        }

        interviewRepository.deleteById(interviewId);
        return ResponseEntity.noContent().build();
    }
}
