package com.alfaztech.acManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alfaztech.acManagement.model.AC;

@Repository
public interface AcRepository extends JpaRepository<AC, Integer>{

}
