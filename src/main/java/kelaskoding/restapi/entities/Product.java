package kelaskoding.restapi.entities;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Table(name = "tbl_products")
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id"
//)
public class Product {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(length = 36, nullable = false, updatable = false)
    private String id;

    //Cara 1 untuk melakukan validasi
    @NotEmpty(message = "Name is required")
    @Column(name="product_name", length = 100)
    private String name;

    @Column(name = "product_description", length = 500)
    private String description;

    private double price;

    public Set<Supplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(Set<Supplier> suppliers) {
        this.suppliers = suppliers;
    }

    //Relasi @ManyToOne, Banyak Produk di satu Kategori = 1 Kategori bisa banyak produk
    @ManyToOne
    private Category category;

    //Relasi @ManyToMany
    @ManyToMany
    //Menghindari pengambilan data infinite-loop relasi
    // a relasi x - x relasi a - a relasi x ...dst
    //alternatifnya bisa pake @JsonIdentityInfo dilevel class (liat atas class)
    //Pake @JoinTable untuk bikin pivot table nya
    @JoinTable(
            name = "tbl_product_supplier",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name="supplier_id")
    )
    private Set<Supplier> suppliers;

    public Product() {
    }

    public Product(String id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
