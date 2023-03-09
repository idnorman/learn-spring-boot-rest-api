package kelaskoding.restapi.repositories;

import kelaskoding.restapi.entities.PlainProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlainProductRepository extends JpaRepository<PlainProduct, String> {
}
