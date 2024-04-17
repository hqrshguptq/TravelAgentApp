package org.example.controller;
import org.example.model.Activity;
import org.example.model.Passenger;
import org.example.model.TravelPackage;
import org.example.service.ActivityService;
import org.example.service.PassengerService;
import org.example.service.TravelPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/travel-packages")
public class PassengerController {

    @Autowired
    private TravelPackageService travelPackageService;

    @Autowired
    private ActivityService activityService;


}
