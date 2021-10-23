package tn.malek.Ecommerce.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.malek.Ecommerce.models.Order;
import tn.malek.Ecommerce.models.OrderDetails;
import tn.malek.Ecommerce.models.Product;
import tn.malek.Ecommerce.models.User;
import tn.malek.Ecommerce.repositories.OrderDetailsRepository;
import tn.malek.Ecommerce.repositories.OrderRepository;
import tn.malek.Ecommerce.repositories.ProductRepository;
import tn.malek.Ecommerce.repositories.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("orders")
public class OrderController {

    private OrderRepository orderRepository;
    private OrderDetailsRepository orderDetailsRepository;
    private UserRepository userRepository;
    private ProductRepository productRepository;

    @Autowired
    public OrderController(
            OrderRepository orderRepository,
            OrderDetailsRepository orderDetailsRepository,
            UserRepository userRepository,
            ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailsRepository = orderDetailsRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }



    @PostMapping(path = "add")
    public ResponseEntity<Map<String, Object>> addCategory(@RequestBody Map<String, Object> newOrder) {

        User user = userRepository.findById((Integer) newOrder.get("idclient")).get();

        Order order = new Order(user, Double.valueOf(newOrder.get("totalPrice").toString()));
        Order savedOrder = orderRepository.save(order);

        List<Map<String, Object>> details = (List<Map<String, Object>>) newOrder.get("products");

        for (Map<String, Object> productDetails : details) {
            Product product = productRepository.findById((Integer) productDetails.get("productId")).get();
            OrderDetails orderDetails = new OrderDetails(null, product, savedOrder, (Integer) productDetails.get("quantity"), product.price);
            orderDetailsRepository.save(orderDetails);
        }

        HashMap<String, Object> response = new HashMap<>();
        response.put("message", "order saved !");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(path = "all")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = this.orderRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }
















}
