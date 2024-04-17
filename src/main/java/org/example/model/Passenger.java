package org.example.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long passengerId;

    private String name;
    private String passengerNumber;
    private String type; // Standard,Gold,Premium
    private double balance;

    @ManyToMany
    private List<TravelPackage> travelPackages;



    @ManyToMany
    @JoinTable(name = "passenger_activity",
            joinColumns = @JoinColumn(name = "passenger_id"),
            inverseJoinColumns = @JoinColumn(name = "activity_id"))
    private List<Activity> activities = new ArrayList<>();

    public boolean hasSufficientBalance(Activity activity) {
        if ("Standard".equals(this.type)) {
            return this.balance >= activity.getCost();
        } else if ("Gold".equals(this.type)) {
            double discountedCost = activity.getCost() * 0.9;
            return this.balance >= discountedCost;
        } else {
            // Premium passenger can sign up for free
            return true;
        }
    }

    public void addActivity(Activity activity) {
        this.activities.add(activity);
        activity.getPassengers().add(this);
    }
}
