package com.slotizen.venus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessProfileRequest {
    public String businessName;
	public String logoUrl;
    public String businessType;
    public String description;
    public String address;
    public String city;
    public String state;
    public String zipCode;
    public String phone;
    public String website;
    public String timezone;
	
}
