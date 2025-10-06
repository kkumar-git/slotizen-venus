package com.slotizen.venus.model;

import com.slotizen.venus.util.LongListConverter;
import com.slotizen.venus.util.StringListConverter;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clients", 
       uniqueConstraints = @UniqueConstraint(columnNames = {"business_id", "email"}))
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "business_id", nullable = false)
    private Long businessId;
    
    @Column(nullable = false, length = 255)
    private String name;
    
    @Column(nullable = false, length = 255)
    private String email;
    
    @Column(length = 50)
    private String phone;
    
    @Column(columnDefinition = "TEXT")
    private String notes;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ClientStatus status = ClientStatus.ACTIVE;
    
    @Column(name = "total_spent", precision = 10, scale = 2)
    private BigDecimal totalSpent = BigDecimal.ZERO;
    
    @Column(name = "total_appointments")
    private Integer totalAppointments = 0;
    
    @Column(name = "last_visit")
    private LocalDate lastVisit;
    
    @Column(name = "next_appointment")
    private LocalDateTime nextAppointment;
    
    @Column(name = "tags", columnDefinition = "json")
    @Convert(converter = StringListConverter.class)
    @JdbcTypeCode(SqlTypes.JSON)
    private List<String> tags = new ArrayList<>();
    
    @Column(name = "favorite_services", columnDefinition = "json")
    @Convert(converter = LongListConverter.class)
    @JdbcTypeCode(SqlTypes.JSON)
    private List<Long> favoriteServices = new ArrayList<>();
    
    @Column(name = "avatar")
    private String avatar;
    
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    
    @Column(name = "join_date")
    private LocalDate joinDate;
    
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @Column(name = "created_by")
    private Long createdBy;
    
    @Column(name = "last_modified_by")
    private Long lastModifiedBy;
    
    // Constructors
    public Client() {}
    
    public Client(Long businessId, String name, String email, String phone, Long createdBy) {
        this.businessId = businessId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.createdBy = createdBy;
        this.lastModifiedBy = createdBy;
        this.joinDate = LocalDate.now();
    }
    
    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getBusinessId() { return businessId; }
    public void setBusinessId(Long businessId) { this.businessId = businessId; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    
    public ClientStatus getStatus() { return status; }
    public void setStatus(ClientStatus status) { this.status = status; }
    
    public BigDecimal getTotalSpent() { return totalSpent; }
    public void setTotalSpent(BigDecimal totalSpent) { this.totalSpent = totalSpent; }
    
    public Integer getTotalAppointments() { return totalAppointments; }
    public void setTotalAppointments(Integer totalAppointments) { this.totalAppointments = totalAppointments; }
    
    public LocalDate getLastVisit() { return lastVisit; }
    public void setLastVisit(LocalDate lastVisit) { this.lastVisit = lastVisit; }
    
    public LocalDateTime getNextAppointment() { return nextAppointment; }
    public void setNextAppointment(LocalDateTime nextAppointment) { this.nextAppointment = nextAppointment; }
    
    public List<String> getTags() { return tags; }
    public void setTags(List<String> tags) { this.tags = tags != null ? tags : new ArrayList<>(); }
    
    public List<Long> getFavoriteServices() { return favoriteServices; }
    public void setFavoriteServices(List<Long> favoriteServices) { this.favoriteServices = favoriteServices != null ? favoriteServices : new ArrayList<>(); }
    
    public String getAvatar() { return avatar; }
    public void setAvatar(String avatar) { this.avatar = avatar; }
    
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    
    public LocalDate getJoinDate() { return joinDate; }
    public void setJoinDate(LocalDate joinDate) { this.joinDate = joinDate; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    
    public Long getCreatedBy() { return createdBy; }
    public void setCreatedBy(Long createdBy) { this.createdBy = createdBy; }
    
    public Long getLastModifiedBy() { return lastModifiedBy; }
    public void setLastModifiedBy(Long lastModifiedBy) { this.lastModifiedBy = lastModifiedBy; }
    
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        if (this.status == null) {
            this.status = ClientStatus.ACTIVE;
        }
        if (this.totalSpent == null) {
            this.totalSpent = BigDecimal.ZERO;
        }
        if (this.totalAppointments == null) {
            this.totalAppointments = 0;
        }
        if (this.joinDate == null) {
            this.joinDate = LocalDate.now();
        }
        if (this.tags == null) {
            this.tags = new ArrayList<>();
        }
        if (this.favoriteServices == null) {
            this.favoriteServices = new ArrayList<>();
        }
    }
    
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}