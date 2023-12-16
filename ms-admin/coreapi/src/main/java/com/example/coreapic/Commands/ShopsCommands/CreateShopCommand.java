package com.example.coreapic.Commands.ShopsCommands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateShopCommand {
    private String shopEmail;
}
