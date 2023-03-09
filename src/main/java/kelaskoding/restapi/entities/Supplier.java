package kelaskoding.restapi.entities;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tbl_suppliers")
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id"
//)
public class Supplier {

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150, nullable = false)
    private String name;

    @Column(length = 200, nullable = false)
    private String address;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    //mapperBy = "suppliers", suppliers adalah nama field yang ada
    //di kelas Product (private Set<Supplier> suppliers <-- ini.
    @ManyToMany(mappedBy = "suppliers")
    //Menghindari pengambilan data infinite-loop relasi
    // a relasi x - x relasi a - a relasi x ...dst
    //alternatifnya bisa pake @JsonIdentityInfo dilevel class (liat atas class)
    @JsonBackReference
    private Set<Product> products;

    public Supplier(){}

    public Supplier(Long id, String name, String address, String email) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
