package edu.icet.service.impl;

import edu.icet.dto.Order;
import edu.icet.entity.OrderEntity;
import edu.icet.repository.OrderRepository;
import edu.icet.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    final OrderRepository repository;

    final ModelMapper mapper;
    @Override
    public void createOrder(Order order) {
        repository.save(mapper.map(order, OrderEntity.class));
    }
}
