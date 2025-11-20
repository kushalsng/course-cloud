package com.elearning.enrollmentservice.controller;

import com.elearning.enrollmentservice.model.Enrollment;
import com.elearning.enrollmentservice.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping
    public Enrollment enroll(@RequestBody Enrollment enrollment) {
        return enrollmentService.enrollUser(enrollment);
    }

    @GetMapping("/user/{userId}")
    public List<Enrollment> getUserEnrollments(@PathVariable Long userId) {
        return enrollmentService.getUserEnrollments(userId);
    }
}
