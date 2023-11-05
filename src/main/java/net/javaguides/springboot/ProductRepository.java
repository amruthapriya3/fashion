package net.javaguides.springboot.product;

import org.springframework.data.jpa.repository.JpaRepository;
import net.javaguides.springboot.product.*;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
