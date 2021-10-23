package tn.malek.Ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.malek.Ecommerce.models.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
