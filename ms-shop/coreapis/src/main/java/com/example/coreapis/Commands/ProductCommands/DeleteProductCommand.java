package com.example.coreapis.Commands.ProductCommands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeleteProductCommand {
    private String productId;

}
