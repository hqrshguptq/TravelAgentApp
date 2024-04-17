package org.example.controller;

import org.example.model.Activity;
import org.example.model.Destination;
import org.example.model.Passenger;
import org.example.model.TravelPackage;
import org.example.service.ActivityService;
import org.example.service.DestinationService;
import org.example.service.PassengerService;
import org.example.service.TravelPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AgencyController {
    @Autowired
    private TravelPackageService travelPackageService;

    @Autowired
    private DestinationService destinationService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private PassengerService passengerService;

    @GetMapping("/travel-packages")
    public List<TravelPackage> getAllTravelPackages() {
        return travelPackageService.getAllTravelPackages();
    }

    @PostMapping("/travel-packages")
    public void createTravelPackage(@RequestBody TravelPackage travelPackage) {
        travelPackageService.saveTravelPackage(travelPackage);
    }

    @GetMapping("/destinations")
    public List<Destination> getAllDestinations() {
        return destinationService.getAllDestinations();
    }

    @PostMapping("/destinations")
    public void createDestination(@RequestBody Destination destination) {
        destinationService.saveDestination(destination);
    }

    @GetMapping("/activities")
    public List<Activity> getAllActivities() {
        return activityService.getAllActivities();
    }

    @PostMapping("/activities")
    public void createActivity(@RequestBody Activity activity) {
        activityService.saveActivity(activity);
    }

    @GetMapping("/passengers")
    public List<Passenger> getAllPassengers() {
        return passengerService.getAllPassengers();
    }

    @PostMapping("/passengers")
    public void createPassenger(@RequestBody Passenger passenger) {
        passengerService.savePassenger(passenger);
    }



}
