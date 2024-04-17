package org.example.service;

import org.example.exceptions.ActivityCapacityReachedException;
import org.example.model.Activity;
import org.example.model.Passenger;
import org.example.model.TravelPackage;
import org.example.repository.TravelPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TravelPackageService {
    @Autowired
    private TravelPackageRepository travelPackageRepository;

    @Autowired
    private PassengerService passengerService;


    public List<TravelPackage> getAllTravelPackages() {
        return travelPackageRepository.findAll();
    }

    public TravelPackage getTravelPackageById(Long travelPackageId) {
        return travelPackageRepository.findById(travelPackageId)
                .orElseThrow(() -> new NoSuchElementException("Travel package not found with ID: " + travelPackageId));
    }

    public void saveTravelPackage(TravelPackage travelPackage) {
        travelPackageRepository.save(travelPackage);
    }

    public void signUpForActivity(TravelPackage travelPackage, Activity activity, Passenger passenger) {

        if (isActivityCapacityReached(travelPackage, activity)) {
            throw new ActivityCapacityReachedException("Activity capacity has been reached.");
        }
        if (travelPackage.getPassengerCapacity()<=0) {
            throw new ActivityCapacityReachedException("Passenger capacity has been reached.");
        }


        passengerService.signingPassengerUpForActivity(passenger.getPassengerId(), activity.getActivityId());
        travelPackage.setPassengerCapacity(travelPackage.getPassengerCapacity() - 1);


        activity.addPassenger(passenger.getPassengerId());
        travelPackage.updateItinerary(activity);
        travelPackageRepository.save(travelPackage);
    }

    private boolean isActivityCapacityReached(TravelPackage travelPackage, Activity activity) {
        return activity.getPassengers().size() >= activity.getCapacity();
    }
}