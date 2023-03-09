package kelaskoding.restapi.services;

import kelaskoding.restapi.entities.PlainProduct;
import kelaskoding.restapi.entities.Product;
import kelaskoding.restapi.entities.Supplier;
import kelaskoding.restapi.repositories.PlainProductRepository;
import kelaskoding.restapi.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {

    private ProductRepository productRepository;
    private PlainProductRepository plainProductRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository, PlainProductRepository plainProductRepository){
        this.productRepository = productRepository;
        this.plainProductRepository = plainProductRepository;
    }

    //if exist, update. if not, create.
    public Product save(Product product){
        return productRepository.save(product);
    }

    @Transactional
    public PlainProduct update(PlainProduct plainProduct){
        return plainProductRepository.save(plainProduct);
    }

    public Product findOne(String id){
        Optional<Product> product = productRepository.findById(id);
        return product.isPresent() ? product.get() : null;
    }

    public Iterable<Product> findAll(){
        return productRepository.findAll();
    }

    public void deleteOne(String id){
        productRepository.deleteById(id);
    }


    public void addSupplier(Supplier supplier, String productId){
        Product product = findOne(productId);
        if(product == null){
            throw new RuntimeException("Product with ID: "+productId+" is not found");
        }
        product.getSuppliers().add(supplier);
        save(product);
    }

    public Product findByProductName(String name){
        return productRepository.findProductByName(name);
    }

    public List<Product> findByProductNameLike(String name){
        //kasi % karna jpa LIKE ngga naroh %, sedangkan query
        //LIKE di sql biasanya ditaroh % supaya bisa mencari string yang
        //mengandung kata tsb
        return productRepository.findProductByNameLike("%"+name+"%");
    }

    public List<Product> findByCategory(Long categoryId){
        return productRepository.findProductByCategory(categoryId);
    }
}
