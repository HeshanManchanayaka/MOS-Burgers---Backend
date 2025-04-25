package edu.icet.service.impl;

import edu.icet.dto.Customer;
import edu.icet.dto.Item;
import edu.icet.dto.Order;
import edu.icet.dto.OrderDetails;
import edu.icet.entity.*;
import edu.icet.repository.CustomerRepository;
import edu.icet.repository.ItemRepository;
import edu.icet.repository.OrderDetsilsRepocitory;
import edu.icet.repository.OrderRepository;
import edu.icet.service.OrderService;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Optional;
import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    final OrderRepository orderRepository;
    final ItemRepository itemRepository;
    final OrderDetsilsRepocitory orderDetsilsRepocitory;
    final CustomerRepository customerRepository;
    final ModelMapper modelMapper;
    final DataSource dataSource;

    @Override
    public void createOrder(Order order) {

        if (order.getOrderDetails() == null || order.getOrderDetails().isEmpty()) {
            throw new RuntimeException("Order must contain at least one product");
        }

        OrderEntity orderEntity = modelMapper.map(order, OrderEntity.class);
        List<OrderDetailsEntity> orderDetailsEntities = new ArrayList<>();

        // Validate all products exist before processing
        for (OrderDetails orderDetails : order.getOrderDetails()) {
            ItemEntity itemEntity = itemRepository.searchByCode(orderDetails.getProductId());
                    //.orElseThrow(() -> new RuntimeException("Product not found: " + orderDetails.getProductId()));

//            // Validate product quantity
//            if (ItemEntity.getQtyInHand() < orderDetails.getOrderQty()) {
//                throw new RuntimeException("Insufficient stock for product: " + orderDetailsDto.getProductId());
//            }
        }

        // Process order details
        for (OrderDetails orderDetails : order.getOrderDetails()) {
            ItemEntity itemEntity = itemRepository.searchByCode(orderDetails.getProductId());

            OrderDetails_PkEntity orderDetailsPkEntity = new OrderDetails_PkEntity(order.getOrderId(), orderDetails.getProductId());
            OrderDetailsEntity orderDetailObj = new OrderDetailsEntity();
            orderDetailObj.setId(orderDetailsPkEntity);
            orderDetailObj.setOrder(orderEntity);
            orderDetailObj.setItem(itemEntity);
            orderDetailObj.setQuantity(orderDetails.getOrderQty());
            orderDetailObj.setDiscount(orderDetails.getDiscount());
            orderDetailObj.setPrice(orderDetails.getPrice());

            orderDetailsEntities.add(orderDetailObj);
        }

        orderEntity.setOrderDetails(orderDetailsEntities);
        orderRepository.save(orderEntity);

        Optional<CustomerEntity> customerOpt = customerRepository.findById(Math.toIntExact(orderEntity.getOrderId()));

        Customer customer = modelMapper.map(customerOpt , Customer.class);
        List<String> orderIdList = new ArrayList<>() ;
        orderIdList.add(String.valueOf(order.getOrderId()));
        customer.setOrderIds(orderIdList);
        System.out.println("Customer DTo  " + customer);
        customerRepository.save(modelMapper.map(customer ,CustomerEntity.class));

        List<OrderDetailsEntity> orderDetailsEntityList = orderDetsilsRepocitory.findByOrderId(String.valueOf(order.getOrderId()));

        orderDetailsEntityList.forEach(orderDetail1 -> {
            Item item = modelMapper.map(itemRepository.findById(orderDetail1.getId().getItemCode()),Item.class);
          //  item.setQtyInHand(item.getQtyInHand()-orderDetail1.getOrderQty());
            itemRepository.save( modelMapper.map(item,ItemEntity.class));
        });
//        OrderEntity orderEntity = modelMapper.map(order, OrderEntity.class);
//        orderEntity.setOrderDate();
//        orderEntity.setStatus("Pending");
//
//        List<OrderDetailsEntity> orderItemEntities = new ArrayList<>();
//
//        order.getOrderItems().forEach(orderItem -> {
//            OrderDetailsEntity orderItemEntity = modelMapper.map(orderItem, OrderDetailsEntity.class);
//            orderItemEntity.setOrder(orderEntity);
//            orderItemEntities.add(orderItemEntity);
//        });
//
//        orderEntity.setOrderItems(orderItemEntities);
//
//        OrderEntity savedOrder = orderRepository.save(orderEntity);
//
//        return modelMapper.map(savedOrder, Order.class);
    }

    @Override
    public Order getOrderById(Long orderId) {
        return null;
    }

    @Override
    public List<Order> getAllOrders() {
        return List.of();
    }

    @Override
    public Order updateOrderStatus(Long orderId, String status) {
        return null;
    }

    @Override
    public void deleteOrder(Long orderId) {

    }

    @Override
    public Order getOrderByName(String name) {
        return null;
    }

    @Override
    public Order getOrderContactNumber(String contactNumber) {
        return null;
    }

    @Override
    public byte[] generateAllOrdersReport() {
        return new byte[0];
    }

    @Override
    public byte[] generateInvoice(String orderId) {
        try {
            File file = ResourceUtils.getFile("classpath:reports/order_invice.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("order_id", orderId);

            JasperPrint print = JasperFillManager.fillReport(
                    jasperReport,
                    parameters,
                    dataSource.getConnection()
            );
            return JasperExportManager.exportReportToPdf(print);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (JRException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
