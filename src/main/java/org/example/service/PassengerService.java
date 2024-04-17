package org.example.service;

import org.example.exceptions.ActivityCapacityReachedException;
import org.example.exceptions.InsufficientBalanceException;
import org.example.model.Activity;
import org.example.model.Passenger;
import org.example.repository.ActivityRepository;
import org.example.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PassengerService {
    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private ActivityService activityService;

    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }

    public Passenger getPassengerById(Long passengerId) {
        return passengerRepository.findById(passengerId)
                .orElseThrow(() -> new NoSuchElementException("Passenger not found with ID: " + passengerId));
    }

    public void savePassenger(Passenger passenger) {
        passengerRepository.save(passenger);
    }


    public void signingPassengerUpForActivity(Long passengerId, Long activityId) {
        Passenger passenger = passengerRepository.findById(passengerId)
                .orElseThrow(() -> new EntityNotFoundException("Passenger not found with id: " + passengerId));

        Activity activity = activityService.getActivityById(activityId);

        if (activity.getPassengers().size() >= activity.getCapacity()) {
            throw new ActivityCapacityReachedException("Activity capacity reached, cannot sign up.");
        }

        if (!passenger.hasSufficientBalance(activity)) {
            throw new InsufficientBalanceException("Insufficient balance to sign up for activity.");
        }
        if ("Standard".equals(passenger.getType())) {
                activity.setCost(activity.getCost());
            } else if ("Gold".equals(passenger.getType())) {
                activity.setCost(activity.getCost() * 0.9);
            } else {
                // Premium passenger can sign up for free
                activity.setCost(0);
            }

        passenger.addActivity(activity);

        passengerRepository.save(passenger);

        updateBalance(passengerId, activity.getCost());

    }

    public void updateBalance(Long passengerId, double cost) {
        Passenger passenger = passengerRepository.findById(passengerId)
                .orElseThrow(() -> new EntityNotFoundException("Passenger not found with id: " + passengerId));

        passenger.setBalance(passenger.getBalance()-cost);
        passengerRepository.save(passenger);
    }



}