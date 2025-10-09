package com.slotizen.venus.repository;

import com.slotizen.venus.model.Booking;
import com.slotizen.venus.model.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    
    // Find all bookings for a business
    List<Booking> findByBusinessIdOrderByBookingDateDescBookingTimeAsc(Long businessId);
    
    // Find bookings by business and status
    List<Booking> findByBusinessIdAndStatus(Long businessId, BookingStatus status);
    
    // Find bookings by business and staff
    List<Booking> findByBusinessIdAndStaff_Id(Long businessId, Long staffId);
    
    // Find bookings by business and specific date
    List<Booking> findByBusinessIdAndBookingDateOrderByBookingTimeAsc(Long businessId, LocalDate bookingDate);
    
    // Find by business and booking ID
    List<Booking> findByBusinessIdAndId(Long businessId, Long bookingId);
    
    // Alternative simpler approach - let's use this instead
    @Query("SELECT b FROM Booking b WHERE b.businessId = :businessId " +
           "AND b.staff.id = :staffId " +
           "AND b.bookingDate = :date " +
           "AND b.status != 'CANCELLED'")
    List<Booking> findBookingsForStaffOnDate(
            @Param("businessId") Long businessId,
            @Param("staffId") Long staffId,
            @Param("date") LocalDate date
    );
    
    // Find bookings by date range
    @Query("SELECT b FROM Booking b WHERE b.businessId = :businessId " +
           "AND b.bookingDate BETWEEN :startDate AND :endDate " +
           "ORDER BY b.bookingDate ASC, b.bookingTime ASC")
    List<Booking> findByBusinessIdAndDateRange(
            @Param("businessId") Long businessId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
    
    // Count bookings for a client
    long countByBusinessIdAndClient_Id(Long businessId, Long clientId);
    
    // Find upcoming bookings for a client
    @Query("SELECT b FROM Booking b WHERE b.businessId = :businessId " +
           "AND b.client.id = :clientId " +
           "AND (b.bookingDate > :currentDate OR " +
           "(b.bookingDate = :currentDate AND b.bookingTime > :currentTime)) " +
           "ORDER BY b.bookingDate ASC, b.bookingTime ASC")
    List<Booking> findUpcomingBookingsForClient(
            @Param("businessId") Long businessId,
            @Param("clientId") Long clientId,
            @Param("currentDate") LocalDate currentDate,
            @Param("currentTime") LocalTime currentTime
    );
}