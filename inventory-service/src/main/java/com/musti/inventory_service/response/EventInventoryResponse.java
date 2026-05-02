package com.musti.inventory_service.response;

import com.musti.inventory_service.entity.Venue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventInventoryResponse {

    private Long eventId;
    private String event;
    private Long capacity;
    private Venue venue;
    private BigDecimal ticketPrice;
}
