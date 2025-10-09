package com.slotizen.venus.service.impl;

import com.slotizen.venus.dto.BookingDto;
import com.slotizen.venus.dto.CreateBookingRequest;
import com.slotizen.venus.exception.BookingConflictException;
import com.slotizen.venus.exception.ResourceNotFoundException;
import com.slotizen.venus.exception.UnauthorizedException;
import com.slotizen.venus.model.*;
import com.slotizen.venus.repository.*;
import com.slotizen.venus.service.BookingService;
import com.slotizen.venus.util.SecurityUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {
    
    @Autowired
    private BookingRepository bookingRepository;
    
    @Autowired
    private ClientRepository clientRepository;
    
    @Autowired
    private ServiceRepository serviceRepository;
    
    @Autowired
    private StaffMemberRepository staffMemberRepository;
    
    @Override
    public List<BookingDto> getBookingsByBusiness(Long businessId) {
        List<Booking> bookings = bookingRepository
            .findByBusinessIdOrderByBookingDateDescBookingTimeAsc(businessId);
        return bookings.stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }
    
    @Override
    public BookingDto createBooking(Long businessId, CreateBookingRequest request) {
        // 1. Validate entities exist
        Client client = clientRepository.findById(request.getClientId())
            .orElseThrow(() -> new ResourceNotFoundException("Client not found"));
        
        ServiceEntity service = serviceRepository.findById(request.getServiceId())
            .orElseThrow(() -> new ResourceNotFoundException("Service not found"));
        
        StaffMember staff = staffMemberRepository.findById(request.getStaffId())
            .orElseThrow(() -> new ResourceNotFoundException("Staff member not found"));
        
        // 2. Check for scheduling conflicts
        LocalTime startTime = LocalTime.parse(request.getTime());
        LocalTime endTime = startTime.plusMinutes(request.getDuration());
        
        // Get all bookings for the staff member on the given date
        List<Booking> existingBookings = bookingRepository.findBookingsForStaffOnDate(
            businessId, staff.getId(), request.getDate()
        );
        
        // Check for conflicts in Java
        for (Booking existingBooking : existingBookings) {
            // Skip cancelled bookings
            if (existingBooking.getStatus() == BookingStatus.CANCELLED) {
                continue;
            }
            
            LocalTime existingStartTime = LocalTime.parse(existingBooking.getBookingTime());
            LocalTime existingEndTime = existingStartTime.plusMinutes(existingBooking.getDurationMinutes());
            
            // Check for time overlap
            boolean hasConflict = (startTime.isBefore(existingEndTime) && endTime.isAfter(existingStartTime));
            
            if (hasConflict) {
                throw new BookingConflictException(
                    String.format("Staff member is already booked from %s to %s",
                            existingStartTime, existingEndTime));
            }
        }
        
        Long currentUserId = SecurityUtils.getCurrentUserId();
        // 3. Create booking
        Booking booking = new Booking(
            businessId, client, service, staff,
            request.getDate(), request.getTime(), request.getDuration(),
            BookingStatus.PENDING, request.getPrice(), request.getNotes(), currentUserId
        );
        
        Booking savedBooking = bookingRepository.save(booking);
        return convertToDto(savedBooking);
    }
    
    @Override
    public BookingDto updateBookingStatus(Long businessId, Long bookingId, String newStatus) {
        Booking booking = bookingRepository.findById(bookingId)
            .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
        
        // Security check
        if (!booking.getBusinessId().equals(businessId)) {
            throw new UnauthorizedException("Not authorized to update this booking");
        }
        
        BookingStatus status = BookingStatus.valueOf(newStatus.toUpperCase().replace("-", "_"));
        booking.setStatus(status);
        
        Booking updated = bookingRepository.save(booking);
        return convertToDto(updated);
    }
    
    @Override
    public void deleteBooking(Long businessId, Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
            .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
        
        if (!booking.getBusinessId().equals(businessId)) {
            throw new UnauthorizedException("Not authorized to delete this booking");
        }
        
        bookingRepository.delete(booking);
    }
    
    @Override
    public List<BookingDto> getBookingsByStatus(Long businessId, String status) {
        BookingStatus bookingStatus = BookingStatus.valueOf(status.toUpperCase().replace("-", "_"));
        List<Booking> bookings = bookingRepository.findByBusinessIdAndStatus(businessId, bookingStatus);
        return bookings.stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }
    
    @Override
    public List<BookingDto> getBookingsByStaff(Long businessId, Long staffId) {
        List<Booking> bookings = bookingRepository.findByBusinessIdAndStaff_Id(businessId, staffId);
        return bookings.stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<BookingDto> getBookingsByDate(Long businessId, LocalDate date) {
        List<Booking> bookings = bookingRepository.findByBusinessIdAndBookingDateOrderByBookingTimeAsc(businessId, date);
        return bookings.stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<BookingDto> getUpcomingBookingsForClient(Long businessId, Long clientId) {
        List<Booking> bookings = bookingRepository.findUpcomingBookingsForClient(
            businessId,
            clientId,
            LocalDate.now(),
            LocalTime.now()
        );
        return bookings.stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    private BookingDto convertToDto(Booking booking) {
        BookingDto dto = new BookingDto();
        dto.setId(booking.getId());
        dto.setClient(getClientName(booking.getClient()));
        dto.setClientAvatar(getInitials(getClientName(booking.getClient())));
        dto.setService(booking.getService().getName());
        dto.setStaff(getStaffName(booking.getStaff()));
        dto.setDate(booking.getBookingDate());
        dto.setTime(booking.getBookingTime());
        dto.setDuration(booking.getDurationMinutes());
        dto.setStatus(booking.getStatus().name().toLowerCase().replace("_", "-"));
        dto.setPrice(booking.getPrice());
        dto.setNotes(booking.getNotes());
        return dto;
    }
    
    private String getClientName(Client client) {
        return client.getName();
    }
    
    private String getStaffName(StaffMember staff) {
        return staff.getFirstName() + " " + staff.getLastName();
    }
    
    private String getInitials(String name) {
        return Arrays.stream(name.split(" "))
            .map(s -> s.substring(0, 1).toUpperCase())
            .collect(Collectors.joining());
    }
}