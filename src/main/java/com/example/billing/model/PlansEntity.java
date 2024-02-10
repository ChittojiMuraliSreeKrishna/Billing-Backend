package com.example.billing.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class PlansEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long planId;
	private String planName;
	
	@OneToMany(mappedBy = "planEntity", cascade = CascadeType.ALL)
	private List<TvChannelsEntity> tvChannels;
	
	@OneToMany(mappedBy = "planEntity", cascade = CascadeType.ALL)
	private List<OttEntity> otts;	
	private double planPrice;

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public List<TvChannelsEntity> getTvChannels() {
		return tvChannels;
	}

	public void setTvChannels(List<TvChannelsEntity> tvChannels) {
		this.tvChannels = tvChannels;
	}

	public List<OttEntity> getOtts() {
		return otts;
	}

	public void setOtts(List<OttEntity> otts) {
		this.otts = otts;
	}

	public double getPlanPrice() {
		return planPrice;
	}

	public void setPlanPrice(double planPrice) {
		this.planPrice = planPrice;
	}

	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}

	public PlansEntity(Long planId, String planName, List<TvChannelsEntity> tvChannels, List<OttEntity> otts,
			double planPrice) {
		super();
		this.planId = planId;
		this.planName = planName;
		this.tvChannels = tvChannels;
		this.otts = otts;
		this.planPrice = planPrice;
	}
	
	
	
}
