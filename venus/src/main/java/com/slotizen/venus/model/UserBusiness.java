package com.slotizen.venus.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "user_business")
@IdClass(UserBusiness.UserBusinessId.class)
public class UserBusiness {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Id
    @Column(name = "business_id")
    private Long businessId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_id", insertable = false, updatable = false)
    private BusinessProfile businessProfile;

    // Constructors
    public UserBusiness() {}

    public UserBusiness(Long userId, Long businessId) {
        this.userId = userId;
        this.businessId = businessId;
    }

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BusinessProfile getBusinessProfile() {
        return businessProfile;
    }

    public void setBusinessProfile(BusinessProfile businessProfile) {
        this.businessProfile = businessProfile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserBusiness)) return false;
        UserBusiness that = (UserBusiness) o;
        return Objects.equals(userId, that.userId) && 
               Objects.equals(businessId, that.businessId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, businessId);
    }

    @Override
    public String toString() {
        return "UserBusiness{" +
                "userId=" + userId +
                ", businessId=" + businessId +
                '}';
    }

    // Composite Primary Key Class
    public static class UserBusinessId implements Serializable {
        private Long userId;
        private Long businessId;

        public UserBusinessId() {}

        public UserBusinessId(Long userId, Long businessId) {
            this.userId = userId;
            this.businessId = businessId;
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public Long getBusinessId() {
            return businessId;
        }

        public void setBusinessId(Long businessId) {
            this.businessId = businessId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof UserBusinessId)) return false;
            UserBusinessId that = (UserBusinessId) o;
            return Objects.equals(userId, that.userId) && 
                   Objects.equals(businessId, that.businessId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(userId, businessId);
        }
    }
}