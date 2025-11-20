package com.elearning.activityservice.service;

import com.elearning.activityservice.model.Activity;
import com.elearning.activityservice.repository.ActivityRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public Activity logActivity(Activity activity) {
        activity.setTimestamp(LocalDateTime.now());
        Activity savedActivity = activityRepository.save(activity);

        // Publish event to RabbitMQ
        String message = "Activity Logged: " + activity.getActivityType() + " for User: " + activity.getUserId();
        rabbitTemplate.convertAndSend("activityExchange", "activityRoutingKey", message);

        return savedActivity;
    }

    public List<Activity> getUserActivities(Long userId) {
        return activityRepository.findByUserId(userId);
    }
}
