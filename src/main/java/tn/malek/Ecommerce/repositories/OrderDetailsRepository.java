package tn.malek.Ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.malek.Ecommerce.models.OrderDetails;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {
}
