package com.slotizen.venus.model;

import jakarta.persistence.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "business_hours")
public class BusinessHours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_id", nullable = false)
    private BusinessProfile businessProfile;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "isOpen", column = @Column(name = "monday_is_open")),
        @AttributeOverride(name = "openTime", column = @Column(name = "monday_open_time")),
        @AttributeOverride(name = "closeTime", column = @Column(name = "monday_close_time"))
    })
    private DailyHours monday;
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "isOpen", column = @Column(name = "tuesday_is_open")),
        @AttributeOverride(name = "openTime", column = @Column(name = "tuesday_open_time")),
        @AttributeOverride(name = "closeTime", column = @Column(name = "tuesday_close_time"))
    })
    private DailyHours tuesday;
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "isOpen", column = @Column(name = "wednesday_is_open")),
        @AttributeOverride(name = "openTime", column = @Column(name = "wednesday_open_time")),
        @AttributeOverride(name = "closeTime", column = @Column(name = "wednesday_close_time"))
    })
    private DailyHours wednesday;
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "isOpen", column = @Column(name = "thursday_is_open")),
        @AttributeOverride(name = "openTime", column = @Column(name = "thursday_open_time")),
        @AttributeOverride(name = "closeTime", column = @Column(name = "thursday_close_time"))
    })
    private DailyHours thursday;
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "isOpen", column = @Column(name = "friday_is_open")),
        @AttributeOverride(name = "openTime", column = @Column(name = "friday_open_time")),
        @AttributeOverride(name = "closeTime", column = @Column(name = "friday_close_time"))
    })
    private DailyHours friday;
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "isOpen", column = @Column(name = "saturday_is_open")),
        @AttributeOverride(name = "openTime", column = @Column(name = "saturday_open_time")),
        @AttributeOverride(name = "closeTime", column = @Column(name = "saturday_close_time"))
    })
    private DailyHours saturday;
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "isOpen", column = @Column(name = "sunday_is_open")),
        @AttributeOverride(name = "openTime", column = @Column(name = "sunday_open_time")),
        @AttributeOverride(name = "closeTime", column = @Column(name = "sunday_close_time"))
    })
    private DailyHours sunday;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public BusinessProfile getBusinessProfile() { return businessProfile; }
    public void setBusinessProfile(BusinessProfile businessProfile) { this.businessProfile = businessProfile; }
    public DailyHours getMonday() { return monday; }
    public void setMonday(DailyHours monday) { this.monday = monday; }
    public DailyHours getTuesday() { return tuesday; }
    public void setTuesday(DailyHours tuesday) { this.tuesday = tuesday; }
    public DailyHours getWednesday() { return wednesday; }
    public void setWednesday(DailyHours wednesday) { this.wednesday = wednesday; }
    public DailyHours getThursday() { return thursday; }
    public void setThursday(DailyHours thursday) { this.thursday = thursday; }
    public DailyHours getFriday() { return friday; }
    public void setFriday(DailyHours friday) { this.friday = friday; }
    public DailyHours getSaturday() { return saturday; }
    public void setSaturday(DailyHours saturday) { this.saturday = saturday; }
    public DailyHours getSunday() { return sunday; }
    public void setSunday(DailyHours sunday) { this.sunday = sunday; }
}

@Converter(autoApply = false)
class LocalTimeStringAttributeConverter implements AttributeConverter<LocalTime, String> {
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("HH:mm");
    public String convertToDatabaseColumn(LocalTime attribute) { return attribute == null ? null : FMT.format(attribute); }
    public LocalTime convertToEntityAttribute(String dbData) { return (dbData == null || dbData.isBlank()) ? null : LocalTime.parse(dbData, FMT); }
}