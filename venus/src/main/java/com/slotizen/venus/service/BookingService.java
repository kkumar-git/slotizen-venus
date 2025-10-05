package com.slotizen.venus.service;

import com.slotizen.venus.dto.BookingDto;
import com.slotizen.venus.dto.CreateBookingRequest;
import java.util.List;

public interface BookingService {
    List<BookingDto> getBookingsByBusiness(Long businessId);
    BookingDto createBooking(Long businessId, CreateBookingRequest request);
    BookingDto updateBookingStatus(Long businessId, Long bookingId, String newStatus);
    void deleteBooking(Long businessId, Long bookingId);
    List<BookingDto> getBookingsByStatus(Long businessId, String status);
    List<BookingDto> getBookingsByStaff(Long businessId, Long staffId);
}