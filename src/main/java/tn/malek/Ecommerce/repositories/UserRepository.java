package tn.malek.Ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.malek.Ecommerce.models.User;

public interface UserRepository extends JpaRepository<User,Integer> {

    User findByEmail(String email);

}
