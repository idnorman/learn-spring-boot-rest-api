package kelaskoding.restapi.repositories;

import kelaskoding.restapi.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long>{

        }