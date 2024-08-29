package com.example.order.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.order.model.Order;
import com.example.order.repository.OrderRepository; 
 
@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
	OrderRepository orderRepository;

	@GetMapping("/orders")
	public ResponseEntity<List<Order>> getAllOrders(@RequestParam(required = false) String user_id) {
		try {
			List<Order> orders= new ArrayList<Order>();

			if (user_id == null)
				orderRepository.findAll().forEach(orders::add);

			if (orders.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(orders, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/orders/{id}")
	public ResponseEntity<Order> getOrderById(@PathVariable("id") long order_id) {
		Optional<Order> ordersData= orderRepository.findById(order_id);

		if (ordersData.isPresent()) {
			return new ResponseEntity<>(ordersData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
    	@PostMapping("/orders")
	public ResponseEntity<Order> createTutorial(@RequestBody Order orders) {
		try {
			Order _order = orderRepository
					.save(new Order(orders.getUser_id(),orders.getName(),orders.getEmail_id(),orders.getPhone(),orders.getProduct(),orders.getQuantity()));
			return new ResponseEntity<>(_order, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@PutMapping("/orders/{order_id}")
	public ResponseEntity<Order> updateTutorial(@PathVariable("order_id") long order_id, @RequestBody Order orders) {
		Optional<Order> orderData = orderRepository.findById(order_id);

		if (orderData.isPresent()) {
			Order _order = orderData.get();
			_order.setUser_id(orders.getUser_id());
			_order.setName(orders.getName());
			_order.setEmail_id(orders.getEmail_id());
            _order.setPhone(orders.getPhone());
            _order.setProduct(orders.getProduct());
            _order.setQuantity(orders.getQuantity());
			return new ResponseEntity<>(orderRepository.save(_order), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/orders/{order_id}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("order_id") long order_id) {
		try {
			orderRepository.deleteById(order_id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/orders")
	public ResponseEntity<HttpStatus> deleteAllorder() {
		try {
			orderRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}