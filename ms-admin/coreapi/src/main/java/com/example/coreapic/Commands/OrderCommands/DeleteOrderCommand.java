package com.example.coreapic.Commands.OrderCommands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteOrderCommand {

        private String orderId;

}
