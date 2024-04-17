package org.example.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long destinationId;

    private String name;

    @ManyToOne
    private TravelPackage travelPackage;

    @OneToMany(mappedBy = "destination", cascade = CascadeType.ALL)
    private List<Activity> activities;

    public void updateActivity(Activity activity) {
        for (int i = 0; i < activities.size(); i++) {
            if (activities.get(i).getActivityId().equals(activity.getActivityId())) {
                activities.set(i, activity);
                break;
            }
        }
    }
}
