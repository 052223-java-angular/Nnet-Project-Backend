package com.NeighborhoodNet.Nnet.services;

import org.springframework.stereotype.Service;

import com.NeighborhoodNet.Nnet.entities.Role;
import com.NeighborhoodNet.Nnet.repositories.RoleRepository;
import com.NeighborhoodNet.Nnet.utils.custome_exceprions.RoleNotFoundException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public Role findByname(String name) {
        return roleRepository.findByname(name).orElseThrow(() -> new RoleNotFoundException("Role " + name + " not found"));
    }
    
}
