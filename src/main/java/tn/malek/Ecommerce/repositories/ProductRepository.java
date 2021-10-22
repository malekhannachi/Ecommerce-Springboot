package tn.malek.Ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.malek.Ecommerce.models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
