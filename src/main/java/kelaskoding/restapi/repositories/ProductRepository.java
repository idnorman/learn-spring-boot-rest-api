package kelaskoding.restapi.repositories;

import kelaskoding.restapi.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.websocket.server.PathParam;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {

    //Ini bukan SQL Query, tapi JPA Query
    //Jadi query bukan ke db, tapi ke Entity/modelnya
    //Product = Entity (tabelnya tbl_product), p.name = properti name dalam Entity Product
    @Query("SELECT p FROM Product p WHERE p.name=:name")
    public Product findProductByName(@PathParam("name") String name);

    @Query("SELECT p FROM Product p WHERE p.name LIKE :name")
    public List<Product> findProductByNameLike(@PathParam("name") String name);

    @Query("SELECT p FROM Product p WHERE p.category.id=:categoryId")
    public List<Product> findProductByCategory(@PathParam("categoryId") Long categoryId);
    
}
