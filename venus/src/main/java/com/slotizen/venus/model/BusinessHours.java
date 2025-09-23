package com.slotizen.venus.model;

import jakarta.persistence.*;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity
@Table(name = "business_hours")
public class BusinessHours {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_id", nullable = false)
    private BusinessProfile businessProfile;

    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "openTime", column = @Column(name = "monday_open_time")),@AttributeOverride(name = "closeTime", column = @Column(name = "monday_close_time")),@AttributeOverride(name = "isOpen", column = @Column(name = "monday_is_open"))})
    private DailyHours monday;
    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "openTime", column = @Column(name = "tuesday_open_time")),@AttributeOverride(name = "closeTime", column = @Column(name = "tuesday_close_time")),@AttributeOverride(name = "isOpen", column = @Column(name = "tuesday_is_open"))})
    private DailyHours tuesday;
    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "openTime", column = @Column(name = "wednesday_open_time")),@AttributeOverride(name = "closeTime", column = @Column(name = "wednesday_close_time")),@AttributeOverride(name = "isOpen", column = @Column(name = "wednesday_is_open"))})
    private DailyHours wednesday;
    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "openTime", column = @Column(name = "thursday_open_time")),@AttributeOverride(name = "closeTime", column = @Column(name = "thursday_close_time")),@AttributeOverride(name = "isOpen", column = @Column(name = "thursday_is_open"))})
    private DailyHours thursday;
    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "openTime", column = @Column(name = "friday_open_time")),@AttributeOverride(name = "closeTime", column = @Column(name = "friday_close_time")),@AttributeOverride(name = "isOpen", column = @Column(name = "friday_is_open"))})
    private DailyHours friday;
    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "openTime", column = @Column(name = "saturday_open_time")),@AttributeOverride(name = "closeTime", column = @Column(name = "saturday_close_time")),@AttributeOverride(name = "isOpen", column = @Column(name = "saturday_is_open"))})
    private DailyHours saturday;
    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "openTime", column = @Column(name = "sunday_open_time")),@AttributeOverride(name = "closeTime", column = @Column(name = "sunday_close_time")),@AttributeOverride(name = "isOpen", column = @Column(name = "sunday_is_open"))})
    private DailyHours sunday;
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public BusinessProfile getBusinessProfile() {
		return businessProfile;
	}
	public void setBusinessProfile(BusinessProfile businessProfile) {
		this.businessProfile = businessProfile;
	}
	public DailyHours getMonday() {
		return monday;
	}
	public void setMonday(DailyHours monday) {
		this.monday = monday;
	}
	public DailyHours getTuesday() {
		return tuesday;
	}
	public void setTuesday(DailyHours tuesday) {
		this.tuesday = tuesday;
	}
	public DailyHours getWednesday() {
		return wednesday;
	}
	public void setWednesday(DailyHours wednesday) {
		this.wednesday = wednesday;
	}
	public DailyHours getThursday() {
		return thursday;
	}
	public void setThursday(DailyHours thursday) {
		this.thursday = thursday;
	}
	public DailyHours getFriday() {
		return friday;
	}
	public void setFriday(DailyHours friday) {
		this.friday = friday;
	}
	public DailyHours getSaturday() {
		return saturday;
	}
	public void setSaturday(DailyHours saturday) {
		this.saturday = saturday;
	}
	public DailyHours getSunday() {
		return sunday;
	}
	public void setSunday(DailyHours sunday) {
		this.sunday = sunday;
	}

    
}

@Embeddable
class DailyHours {

    private Boolean isOpen;

    private LocalTime openTime;

    private LocalTime closeTime;

    public Boolean getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Boolean open) {
        this.isOpen = open;
    }

    public LocalTime getOpenTime() {
        return openTime;
    }

    public void setOpenTime(LocalTime openTime) {
        this.openTime = openTime;
    }

    public LocalTime getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(LocalTime closeTime) {
        this.closeTime = closeTime;
    }
}

@Converter(autoApply = false)
class LocalTimeStringAttributeConverter implements AttributeConverter<LocalTime, String> {
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("HH:mm");
    @Override
    public String convertToDatabaseColumn(LocalTime attribute) {
        return attribute == null ? null : FMT.format(attribute);
    }
    @Override
    public LocalTime convertToEntityAttribute(String dbData) {
        return (dbData == null || dbData.isBlank()) ? null : LocalTime.parse(dbData, FMT);
    }
}
