package com.elearning.activityservice.controller;

import com.elearning.activityservice.model.Activity;
import com.elearning.activityservice.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @PostMapping
    public Activity logActivity(@RequestBody Activity activity) {
        return activityService.logActivity(activity);
    }

    @GetMapping("/user/{userId}")
    public List<Activity> getUserActivities(@PathVariable Long userId) {
        return activityService.getUserActivities(userId);
    }
}
