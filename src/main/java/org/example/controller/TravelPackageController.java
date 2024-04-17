package org.example.controller;

import org.example.exceptions.ActivityCapacityReachedException;
import org.example.exceptions.InsufficientBalanceException;
import org.example.model.Activity;
import org.example.model.Passenger;
import org.example.model.TravelPackage;
import org.example.service.ActivityService;
import org.example.service.PassengerService;
import org.example.service.TravelPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/travel-packages")
public class TravelPackageController {

    @Autowired
    private TravelPackageService travelPackageService;
    @Autowired
    private PassengerService passengerService;
    @Autowired
    private ActivityService activityService;

    @PostMapping("/{travelPackageId}/activities/{activityId}/passengers/{passengerId}/signup")
    public ResponseEntity<?> signUpForActivity(
            @PathVariable Long travelPackageId,
            @PathVariable Long activityId,
            @PathVariable Long passengerId) {

        TravelPackage travelPackage = travelPackageService.getTravelPackageById(travelPackageId);
        Activity activity = activityService.getActivityById(activityId);
        Passenger passenger = passengerService.getPassengerById(passengerId);

        // Checking if the entities exist and if the travel package is associated with the activity
        if (travelPackage == null || activity == null || passenger == null) {
            return ResponseEntity.notFound().build();
        }

        // Call the signUpForActivity method to sign up the passenger for the activity
        try {
            travelPackageService.signUpForActivity(travelPackage, activity, passenger);
            return ResponseEntity.ok().build();
        } catch (ActivityCapacityReachedException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Activity capacity reached.");
        } catch (InsufficientBalanceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient balance.");
        }
    }
}
