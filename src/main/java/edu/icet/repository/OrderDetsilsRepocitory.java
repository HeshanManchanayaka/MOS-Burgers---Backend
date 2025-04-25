package edu.icet.repository;

import edu.icet.entity.OrderDetailsEntity;

import java.util.List;

public interface OrderDetsilsRepocitory {
    List<OrderDetailsEntity> findByOrderId(String orderId);
}
