package com.musti.inventory_service.controller;

import com.musti.inventory_service.response.EventInventoryResponse;
import com.musti.inventory_service.response.VenueInventoryResponse;
import com.musti.inventory_service.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/api/inventory")
public class InventoryController {

    private InventoryService inventoryService;

    @Autowired
    public InventoryController(final InventoryService inventoryService){
        this.inventoryService = inventoryService;
    }

    @GetMapping("/events/list")
    public @ResponseBody List<EventInventoryResponse> inventoryGetAllEvents(){
        return inventoryService.getAllEvents();
    }

    @GetMapping("/venue/{venueId}")
    public @ResponseBody VenueInventoryResponse inventoryByVenueId(@PathVariable("venueId") Long venueId){
        return inventoryService.getVenueInformation(venueId);
    }

    @GetMapping("/event/{eventId}")
    public @ResponseBody EventInventoryResponse inventoryForEvent(@PathVariable ("eventId") Long eventId){
        return inventoryService.getEventInventory(eventId);
    }
}
