package com.ciclo4.reto.repositorio;


import com.ciclo4.reto.interfaces.InterfaceOrder;
import com.ciclo4.reto.modelo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepositorio {

    @Autowired
    private MongoTemplate mongoTemplate;


    @Autowired
    private InterfaceOrder interfaceorder;

    public List<Order> getAll() {
        return (List<Order>) interfaceorder.findAll();
    }

    public Optional<Order> getOrder(int id) {
        return interfaceorder.findById(id);
    }

    public Order create(Order order) {
        return interfaceorder.save(order);
    }

    public void update(Order order) {
        interfaceorder.save(order);
    }

    public void delete(Order order) {
        interfaceorder.delete(order);
    }

    public Optional<Order> lastUserId(){
        return interfaceorder.findTopByOrderByIdDesc();
    }

    public List<Order> findByZone(String zone) {
        return interfaceorder.findByZone(zone);
    }

    public List<Order> ordersSalesManByID(Integer id) {
        Query query = new Query();

        Criteria criterio = Criteria.where("salesMan.id").is(id);
        query.addCriteria(criterio);

        List<Order> orders = mongoTemplate.find(query, Order.class);

        return orders;
    }

    public List<Order> ordersSalesManByState(String state, Integer id) {
        Query query = new Query();

        Criteria criterio = Criteria.where("salesMan.id").is(id)
                .and("status").is(state);
        query.addCriteria(criterio);

        List<Order> orders = mongoTemplate.find(query,Order.class);

        return orders;
    }

    public List<Order> ordersSalesManByDate(String dateStr, Integer id) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Query query = new Query();

        Criteria criterio = Criteria.where("registerDay")
                .gte(LocalDate.parse(dateStr, dtf).minusDays(1).atStartOfDay())
                .lt(LocalDate.parse(dateStr, dtf).plusDays(1).atStartOfDay())
                .and("salesMan.id").is(id);
        query.addCriteria(criterio);

        List<Order> orders = mongoTemplate.find(query,Order.class);

        return orders;
    }

}
