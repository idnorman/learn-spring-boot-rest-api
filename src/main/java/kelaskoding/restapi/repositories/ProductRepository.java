package kelaskoding.restapi.repositories;

import kelaskoding.restapi.entities.Product;
import kelaskoding.restapi.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.websocket.server.PathParam;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {

    //Ini bukan SQL Query, tapi JPA Query
    //Jadi query bukan ke db, tapi ke Entity/modelnya
    //Product = Entity (tabelnya tbl_product), p.name = properti name dalam Entity Product
    @Query("SELECT p FROM Product p WHERE p.name=:name")
    Product findProductByName(@PathParam("name") String name);

    @Query("SELECT p FROM Product p WHERE p.name LIKE :name")
    List<Product> findProductByNameLike(@PathParam("name") String name);

    @Query("SELECT p FROM Product p WHERE p.category.id=:categoryId")
    List<Product> findProductByCategory(@PathParam("categoryId") Long categoryId);

    @Query("SELECT p FROM Product p WHERE :supplier MEMBER OF p.suppliers")
    List<Product> findProductBySupplier(@PathParam("supplier") Supplier supplier);
}
