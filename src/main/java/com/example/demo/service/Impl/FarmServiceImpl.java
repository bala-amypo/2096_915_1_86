package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Farm;
import com.example.demo.entity.User;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.FarmRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.FarmService;

@Service
public class FarmServiceImpl implements FarmService {

    @Autowired
    private FarmRepository farmRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Farm addFarm(Farm farm, Long userId) {

        if (farm.getSoilPH() < 0 || farm.getSoilPH() > 14) {
            throw new BadRequestException("Invalid soil pH value");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id " + userId));

        farm.setOwner(user);

        return farmRepository.save(farm);
    }

    @Override
    public Farm getFarmById(Long id) {

        return farmRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Farm not found with id " + id));
    }

    @Override
    public List<Farm> getFarmsByOwner(Long ownerId) {
        return farmRepository.findByOwnerId(ownerId);
    }
}
