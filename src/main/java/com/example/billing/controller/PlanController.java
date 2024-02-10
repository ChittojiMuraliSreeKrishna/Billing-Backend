package com.example.billing.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.billing.model.PlansEntity;
import com.example.repository.OttRepository;
import com.example.repository.PlanRepository;
import com.example.repository.TvChannelRepository;

@RestController
public class PlanController{
	
	private final PlanRepository planRepository;
	private final TvChannelRepository tvChannelRepository;
	private final OttRepository ottRepository;
	
	public PlanController(PlanRepository planRepository, TvChannelRepository tvChannelRepository, OttRepository ottRepository) {
		this.planRepository = planRepository;
		this.tvChannelRepository = tvChannelRepository;
		this.ottRepository = ottRepository;
	}
	
	@PostMapping("/plans")
	public void createPlan(@RequestBody List<PlansEntity> plans) {
		for (PlansEntity plans1 : plans) {
			PlansEntity savedPlan = (PlansEntity) planRepository.saveAll(plans);
		}
	}
	
}
