package edu.icet.controller;

import edu.icet.dto.Order;
import edu.icet.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
@CrossOrigin
public class OrderController {

    final OrderService orderService;

    @PostMapping("/create")
    public void createOrder(@RequestBody Order order){
        orderService.createOrder(order);
    }

}
