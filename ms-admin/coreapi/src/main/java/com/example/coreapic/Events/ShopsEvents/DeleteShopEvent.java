package com.example.coreapic.Events.ShopsEvents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteShopEvent {
    private String eventIdentifier;

    private String shopId;
    private String shopEmail;
}
