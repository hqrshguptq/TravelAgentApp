package org.example.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
public class Activity {

    private PassengerService passengerService;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long activityId;

    private String name;
    private String description;
    private double cost;
    private int capacity;

    @ManyToOne
    private Destination destination;

    @ManyToMany(mappedBy = "activities")
    private List<Passenger> passengers;


    public void addPassenger(Long passengerId) {
        if (passengers == null) {
            passengers = new ArrayList<>();
        }
        Passenger passenger = passengerService.getPassengerById(passengerId);
        if (passenger != null) {
            passengers.add(passenger);
        }
    }
}
