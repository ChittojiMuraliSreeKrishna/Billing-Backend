package com.example.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.billing.model.OttEntity;
public interface OttRepository extends JpaRepository<OttEntity, Long> {
	

}
