package com.alfaztech.acManagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alfaztech.acManagement.model.AC;
import com.alfaztech.acManagement.repository.AcRepository;

@Service
public class AcService {
	
	@Autowired
	private AcRepository acRepo;
	
	public AC saveAC(AC ac) {
		return acRepo.save(ac);
	}
	
	public AC updateDish(Integer id, AC updatedAC) {
        AC existingAC = acRepo.findById(id).orElseThrow(() -> new RuntimeException("AC not found"));
        existingAC.setBrandName(updatedAC.getBrandName());
        existingAC.setModelName(updatedAC.getModelName());
        existingAC.setType(updatedAC.getType());
        existingAC.setCapacity(updatedAC.getCapacity());
        existingAC.setStar(updatedAC.getStar());
        existingAC.setColor(updatedAC.getColor());
        existingAC.setYear(updatedAC.getYear());
        existingAC.setAge(updatedAC.getAge());
        existingAC.setImageUrl(updatedAC.getImageUrl());
        return acRepo.save(existingAC);
    }

    public void deleteAc(Integer id) {
    	acRepo.deleteById(id);
    }

    public Optional<AC> getAcById(Integer id) {
        return acRepo.findById(id);
    }

    public List<AC> getAllAc() {
        return acRepo.findAll();
    }


}
