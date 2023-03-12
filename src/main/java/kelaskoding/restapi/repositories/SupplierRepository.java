package kelaskoding.restapi.repositories;

import kelaskoding.restapi.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Long>{

        //Derived Query
        //Query otomatis oleh JPA berdasarkan nama method
        //Pake field yang dientity, bukan column di db.

        Supplier findByEmail(String email);
        List<Supplier> findByNameContains(String name);
        List<Supplier> findByNameStartsWith(String prefix);
        List<Supplier> findByNameContainsOrEmailContains(String name, String email);

}