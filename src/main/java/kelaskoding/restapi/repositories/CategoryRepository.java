package kelaskoding.restapi.repositories;

import kelaskoding.restapi.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
