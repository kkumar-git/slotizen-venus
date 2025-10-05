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
    
    /**
     * Find all bookings for a specific business ordered by date and time
     */
    List<Booking> findByBusinessIdOrderByBookingDateDescBookingTimeDesc(Long businessId);
    
    /**
     * Find bookings by status for a business
     */
    List<Booking> findByBusinessIdAndStatus(Long businessId, BookingStatus status);
    
    /**
     * Find bookings for a specific date
     */
    List<Booking> findByBusinessIdAndBookingDate(Long businessId, LocalDate date);
    
    /**
     * Find bookings for a specific staff member
     */
    List<Booking> findByBusinessIdAndStaffId(Long businessId, Long staffId);
    
    /**
     * Find bookings for a specific client
     */
    List<Booking> findByBusinessIdAndClientId(Long businessId, Long clientId);
    
    /**
     * Check for scheduling conflicts (same staff, overlapping time)
     */
    @Query("SELECT b FROM Booking b WHERE b.businessId = :businessId " +
           "AND b.staff.id = :staffId " +
           "AND b.bookingDate = :date " +
           "AND b.status != 'CANCELLED' " +
           "AND ((:startTime >= b.bookingTime AND :startTime < FUNCTION('ADDTIME', b.bookingTime, FUNCTION('SEC_TO_TIME', b.durationMinutes * 60))) " +
           "OR (b.bookingTime >= :startTime AND b.bookingTime < :endTime))")
    List<Booking> findConflictingBookings(
        @Param("businessId") Long businessId,
        @Param("staffId") Long staffId,
        @Param("date") LocalDate date,
        @Param("startTime") LocalTime startTime,
        @Param("endTime") LocalTime endTime
    );
    
    /**
     * Find bookings by business and date range
     */
    @Query("SELECT b FROM Booking b WHERE b.businessId = :businessId " +
           "AND b.bookingDate BETWEEN :startDate AND :endDate " +
           "ORDER BY b.bookingDate ASC, b.bookingTime ASC")
    List<Booking> findByBusinessIdAndDateRange(
        @Param("businessId") Long businessId,
        @Param("startDate") LocalDate startDate,
        @Param("endDate") LocalDate endDate
    );
    
    /**
     * Count bookings by status for statistics
     */
    @Query("SELECT COUNT(b) FROM Booking b WHERE b.businessId = :businessId AND b.status = :status")
    Long countByBusinessIdAndStatus(@Param("businessId") Long businessId, @Param("status") BookingStatus status);
}