package com.example.laptopschoolserviceapp.services;

import com.example.laptopschoolserviceapp.models.Laptop;
import com.example.laptopschoolserviceapp.models.LaptopPart;
import com.example.laptopschoolserviceapp.repositories.LaptopPartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LaptopPartService {

    @Autowired
    private LaptopPartRepository laptopPartRepository;

    public LaptopPartService(LaptopPartRepository laptopPartRepository) {
        this.laptopPartRepository = laptopPartRepository;
    }

    public List<LaptopPart> getAll(){
        return laptopPartRepository.findAll();
    }
    public LaptopPart create(LaptopPart part){
        return laptopPartRepository.save(part);
    };
    public LaptopPart save(LaptopPart part){
        return laptopPartRepository.save(part);
    }

    public void delete(Long id){
        laptopPartRepository.deleteById(id);
    };

    public LaptopPart findById(Long id) {
        Optional<LaptopPart> optionalProject = laptopPartRepository.findById(id);
        if(optionalProject.isPresent()) {
            return optionalProject.get();
        }else {
            return null;
        }
    }
}
