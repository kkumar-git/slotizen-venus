package com.slotizen.venus.controller;

import com.slotizen.venus.dto.ApiResponse;
import com.slotizen.venus.dto.BookingDto;
import com.slotizen.venus.dto.CreateBookingRequest;
import com.slotizen.venus.dto.UpdateBookingStatusRequest;
import com.slotizen.venus.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("/businesses/{businessId}/bookings")
@PreAuthorize("hasAnyRole('STAFF', 'CLIENT_MANAGER', 'CLIENT', 'ADMIN', 'SUPER_ADMIN', 'BUSINESS_OWNER')")
public class BookingController {
    
    @Autowired
    private BookingService bookingService;
    
    /**
     * Get all bookings for a business, optionally filtered by date
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<BookingDto>>> getBookings(
            @PathVariable("businessId") Long businessId,
            @RequestParam(value = "date", required = false) String dateStr) {
        
        List<BookingDto> bookings;
        
        if (dateStr != null && !dateStr.trim().isEmpty()) {
            try {
                LocalDate date = LocalDate.parse(dateStr);
                bookings = bookingService.getBookingsByDate(businessId, date);
            } catch (DateTimeParseException e) {
                return ResponseEntity.badRequest()
                    .body(new ApiResponse<>(false, "Invalid date format. Please use YYYY-MM-DD format.", null));
            }
        } else {
            bookings = bookingService.getBookingsByBusiness(businessId);
        }
        
        return ResponseEntity.ok(new ApiResponse<>(true, "Bookings retrieved successfully", bookings));
    }
    
    /**
     * Create a new booking
     */
    @PostMapping
    public ResponseEntity<ApiResponse<BookingDto>> createBooking(
            @PathVariable("businessId") Long businessId,
            @Valid @RequestBody CreateBookingRequest request) {
        
        BookingDto booking = bookingService.createBooking(businessId, request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Booking created successfully", booking));
    }
    
    /**
     * Update booking status
     */
    @PutMapping("/{bookingId}/status")
    public ResponseEntity<ApiResponse<BookingDto>> updateBookingStatus(
            @PathVariable("businessId") Long businessId,
            @PathVariable("bookingId") Long bookingId,
            @Valid @RequestBody UpdateBookingStatusRequest request) {
        BookingDto booking = bookingService.updateBookingStatus(businessId, bookingId, request.getStatus());
        return ResponseEntity.ok(new ApiResponse<>(true, "Booking status updated successfully", booking));
    }
    
    /**
     * Cancel/Delete booking
     */
    @DeleteMapping("/{bookingId}")
    public ResponseEntity<ApiResponse<String>> deleteBooking(
            @PathVariable("businessId") Long businessId,
            @PathVariable("bookingId") Long bookingId) {
        bookingService.deleteBooking(businessId, bookingId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Booking deleted successfully", null));
    }
    
    /**
     * Get bookings by status
     */
    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponse<List<BookingDto>>> getBookingsByStatus(
            @PathVariable("businessId") Long businessId,
            @PathVariable("status") String status) {
        List<BookingDto> bookings = bookingService.getBookingsByStatus(businessId, status);
        return ResponseEntity.ok(new ApiResponse<>(true, "Bookings retrieved successfully", bookings));
    }
    
    /**
     * Get bookings by staff member
     */
    @GetMapping("/staff/{staffId}")
    public ResponseEntity<ApiResponse<List<BookingDto>>> getBookingsByStaff(
            @PathVariable("businessId") Long businessId,
            @PathVariable("staffId") Long staffId) {
        List<BookingDto> bookings = bookingService.getBookingsByStaff(businessId, staffId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Staff bookings retrieved successfully", bookings));
    }
}