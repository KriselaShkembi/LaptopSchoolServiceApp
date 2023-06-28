package com.example.laptopschoolserviceapp.services;

import com.example.laptopschoolserviceapp.models.Laptop;
import com.example.laptopschoolserviceapp.repositories.LaptopRepository;
import com.example.laptopschoolserviceapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LaptopService {

    @Autowired
    private LaptopRepository laptopRepository;
    private final UserRepository userRepository;

    public LaptopService(LaptopRepository laptopRepository,
                         UserRepository userRepository) {
        this.laptopRepository = laptopRepository;
        this.userRepository = userRepository;
    }

    public List<Laptop> getAll(){
        return laptopRepository.findAll();
    }
    public Laptop create(Laptop laptop){
        return laptopRepository.save(laptop);
    }
    public Laptop save(Laptop laptop){
        return laptopRepository.save(laptop);
    }

    public void delete(Long id){
        laptopRepository.deleteById(id);
    };

    public Laptop findById(Long id) {
        Optional<Laptop> optionalLaptop = laptopRepository.findById(id);
        if(optionalLaptop.isPresent()) {
            return optionalLaptop.get();
        }else {
            return null;
        }
    }

    public List<Laptop> findLaptopsByUserId(Long userId) {
        if(userId == null){
            return null;
        }
        List<Laptop> userLaptops = laptopRepository.findByUserId(userId);
        return userLaptops;
    }
}
