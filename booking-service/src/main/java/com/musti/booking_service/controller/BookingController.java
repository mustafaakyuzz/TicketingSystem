package com.musti.booking_service.controller;

import com.musti.booking_service.request.BookingRequest;
import com.musti.booking_service.response.BookingResponse;
import com.musti.booking_service.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api/booking")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json", path = "/create")
    BookingResponse createBooking(@RequestBody BookingRequest request){
        return bookingService.createBooking(request);
    }
}
