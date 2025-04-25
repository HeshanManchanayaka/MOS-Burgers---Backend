package edu.icet.controller;

import edu.icet.dto.Order;
import edu.icet.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/getOrderById")
    public Order getOrder(@RequestParam Long orderId){
        return orderService.getOrderById(orderId);
    }

    @GetMapping("/getAll")
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOrder (@PathVariable Long orderId){
        orderService.deleteOrder(orderId);
    }

    @GetMapping("/search-by-name/{name}")
    public Order getOrderByName(@PathVariable String name ){
        return orderService.getOrderByName(name);
    }

    @GetMapping("/search-by-contactNumber/{contactNumber}")
    public Order getOrderContactNumber(String contactNumber){
        return orderService.getOrderContactNumber(contactNumber);
    }

    @GetMapping("/invoice/all")
    public ResponseEntity<byte[]> generateAllOrdersReport() {
        try {
            byte[] report = orderService.generateAllOrdersReport();
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=all_orders.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(report);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/invoice/{orderId}")
    public ResponseEntity<byte[]> generateInvoice(@PathVariable String orderId) {
        try {
            byte[] invoice = orderService.generateInvoice(orderId);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=invoice_" + orderId + ".pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(invoice);
        } catch (Exception e) {
            e.printStackTrace(); // For debugging
            return ResponseEntity.internalServerError().build();
        }
    }

}
