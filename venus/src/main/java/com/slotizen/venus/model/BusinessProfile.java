package com.slotizen.venus.model;

import jakarta.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "business_profile")
public class BusinessProfile {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID businessId;

	@Column(nullable = false)
	private String businessName;

	@Column(length = 500)
	private String logoUrl;

	@Column(nullable = false)
	private String businessType;

	@Column(length = 1000)
	private String description;

	@Column(nullable = false)
	private String address;
	private String city;
	private String state;
	private String zipCode;
	private String phone;
	private String website;
	private String timezone;
	@Column(nullable = false, unique = true)
	private String slug;
	private boolean active = true;
	private Integer competitionLevel;
	private boolean completed = false;
	@Column(nullable = false)
	private ZonedDateTime createdAt = ZonedDateTime.now();

	public UUID getBusinessId() {
		return businessId;
	}

	public void setBusinessId(UUID businessId) {
		this.businessId = businessId;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public ZonedDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(ZonedDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Integer getCompetitionLevel() {
		return competitionLevel;
	}

	public void setCompetitionLevel(Integer competitionLevel) {
		this.competitionLevel = competitionLevel;
	}

	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

}
