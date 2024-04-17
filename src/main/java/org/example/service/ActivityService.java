package org.example.service;

import org.example.model.Activity;
import org.example.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ActivityService {
    @Autowired
    private ActivityRepository activityRepository;

    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    public void saveActivity(Activity activity) {
        activityRepository.save(activity);
    }

    public Activity getActivityById(Long id) {
        Optional<Activity> activityOptional = activityRepository.findById(id);
        if (activityOptional.isPresent()) {
            return activityOptional.get();
        } else {
            throw new EntityNotFoundException("Activity not found with id: " + id);
        }
    }
}