package edu.icet.service;

import edu.icet.dto.Order;

import java.util.List;

public interface OrderService {
    void createOrder(Order order);
    Order getOrderById(Long orderId);
    List<Order> getAllOrders();
    Order updateOrderStatus(Long orderId, String status);
    void deleteOrder(Long orderId);

    Order getOrderByName(String name);

    Order getOrderContactNumber(String contactNumber);

    byte[] generateAllOrdersReport();

    byte[] generateInvoice(String orderId);
}
