package tn.malek.Ecommerce.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import tn.malek.Ecommerce.models.Category;

public interface CategoryRepository extends JpaRepository <Category,Integer> {


}
