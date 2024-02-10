package com.example.billing.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class OttEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@ManyToOne
	@JoinColumn(name = "planEntity_planId")
	private PlansEntity planEntity;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public PlansEntity getPlanEntity() {
		return planEntity;
	}
	public void setPlanEntity(PlansEntity planEntity) {
		this.planEntity = planEntity;
	}
	public OttEntity(Long id, String name, PlansEntity planEntity) {
		super();
		this.id = id;
		this.name = name;
		this.planEntity = planEntity;
	}
	
	
	
	
}
