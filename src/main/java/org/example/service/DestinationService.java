package org.example.service;

import org.example.model.Destination;
import org.example.repository.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DestinationService {
    @Autowired
    private DestinationRepository destinationRepository;

    public List<Destination> getAllDestinations() {
        return destinationRepository.findAll();
    }

    public void saveDestination(Destination destination) {
        destinationRepository.save(destination);
    }

}