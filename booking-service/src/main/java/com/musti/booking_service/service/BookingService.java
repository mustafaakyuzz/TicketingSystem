package com.musti.booking_service.service;

import com.musti.booking_service.client.InventoryServiceClient;
import com.musti.booking_service.entity.Customer;
import com.musti.booking_service.repository.CustomerRepository;
import com.musti.booking_service.request.BookingRequest;
import com.musti.booking_service.response.BookingResponse;
import com.musti.booking_service.response.InventoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
    private final CustomerRepository customerRepository;
    private final InventoryServiceClient inventoryServiceClient;

    @Autowired
    public BookingService(final CustomerRepository customerRepository,
                          final InventoryServiceClient inventoryServiceClient){
        this.customerRepository = customerRepository;
        this.inventoryServiceClient = inventoryServiceClient;
    }

    public BookingResponse createBooking(final BookingRequest request){
        // Check if the user exists
        final Customer customer = customerRepository.findById(request.getUserId()).orElse(null);
        if(customer==null){
            throw new RuntimeException("User Not Found");
        }
        // Check if there is enough inventory
        final InventoryResponse inventoryResponse = inventoryServiceClient.getInventory(request.getEventId());
        System.out.println("Inventory Service Response: "+ inventoryResponse);
        if(inventoryResponse.getCapacity() < request.getTicketCount()){
            throw new RuntimeException("Not Enough Inventory");
        }

        return BookingResponse.builder().build();
    }
}
