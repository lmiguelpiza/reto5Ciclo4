package com.ciclo4.reto.servicio;


import com.ciclo4.reto.modelo.Order;
import com.ciclo4.reto.repositorio.OrderRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {


    @Autowired
    private OrderRepositorio orderRepositorio;

    public List<Order> getAll() {
        return orderRepositorio.getAll();
    }

    public Optional<Order> getOrder(int id) {
        return orderRepositorio.getOrder(id);
    }

    public Order create(Order order) {

        Optional<Order> orderIdMaxima = orderRepositorio.lastUserId();

        if (order.getId() == null) {
                if (orderIdMaxima.isEmpty())
                order.setId(1);
                else
                order.setId(orderIdMaxima.get().getId() + 1);
        }

        Optional<Order> e = orderRepositorio.getOrder(order.getId());
        if (e.isEmpty()) {
            return orderRepositorio.create(order);
        }else{
            return order;
        }
    }

    public Order update(Order order) {

        if (order.getId() != null) {
            Optional<Order> orderDb = orderRepositorio.getOrder(order.getId());
            if (!orderDb.isEmpty()) {
                if (order.getStatus() != null) {
                    orderDb.get().setStatus(order.getStatus());
                }
                orderRepositorio.update(orderDb.get());
                return orderDb.get();
            } else {
                return order;
            }
        } else {
            return order;
        }
    }

    public boolean delete(int id) {
        return getOrder(id).map(order -> {
            orderRepositorio.delete(order);
            return true;
        }).orElse(false);
    }

    public List<Order> findByZone(String zone) {
        return orderRepositorio.findByZone(zone);
    }

    public List<Order> ordersSalesManByID(int id){
        return orderRepositorio.ordersSalesManByID(id);
    }

    public List<Order> ordersSalesManByState(String state, int id){
        return orderRepositorio.ordersSalesManByState(state, id);
    }

    public List<Order> ordersSalesManByDate(String dateStr, Integer id) {
        return orderRepositorio.ordersSalesManByDate(dateStr,id);
    }

}
