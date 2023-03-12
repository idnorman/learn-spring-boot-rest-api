package kelaskoding.restapi.controllers;

import kelaskoding.restapi.dto.ResponseData;
import kelaskoding.restapi.dto.SearchData;
import kelaskoding.restapi.entities.PlainProduct;
import kelaskoding.restapi.entities.Product;
import kelaskoding.restapi.entities.Supplier;
import kelaskoding.restapi.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    //dikasi anotasi requestbody, karena datanya dikirim lewat situ

    //anotasi valid menandakan bahwa request harus dicek sesuai
    //validasi yang dibuat di Model/Entity Product dan akan mengirimkan
    //Errors (Errors errors seperti di parameter)
    public ResponseEntity<ResponseData<Product>> create(@Valid @RequestBody Product product, Errors errors){

        ResponseData responseData = new ResponseData<>();

        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(product);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        responseData.setStatus(true);
        responseData.setPayload(productService.save(product));
        return ResponseEntity.status(HttpStatus.CREATED).body(responseData);
    }

    @GetMapping("/get")
    public Iterable<Product> findAll(){
        return productService.findAll();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Product> findOne(@PathVariable("id") String id){
        Product product = productService.findOne(id);
        return product == null ? new ResponseEntity<Product>(HttpStatus.NOT_FOUND) : new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @PutMapping("/update")
    //dikasi anotasi requestbody, karena datanya dikirim lewat situ
    public ResponseEntity<ResponseData<PlainProduct>> update(@Valid @RequestBody PlainProduct plainProduct, Errors errors){
        ResponseData responseData = new ResponseData<>();

        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(plainProduct);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        responseData.setStatus(true);

        responseData.setPayload(productService.update(plainProduct));
        return ResponseEntity.ok().body(responseData);
    }

    @DeleteMapping("delete/{id}")
    public void deleteOne(@PathVariable("id") String id){
        productService.deleteOne(id);
    }

    @PostMapping("/add/supplier/{id}")
    public void addSupplier(@RequestBody Supplier supplier, @PathVariable("id") String productId){
        productService.addSupplier(supplier, productId);
    }

    @PostMapping("/search/name")
    public Product getProductByName(@RequestBody SearchData searchData){
        return productService.findByProductName(searchData.getSearchKey());
    }

    @PostMapping("/search/name-like")
    public List<Product> getProductByNameLike(@RequestBody SearchData searchData){
        return productService.findByProductNameLike(searchData.getSearchKey());
    }

    @GetMapping("/search/category/{categoryId}")
    public List<Product> getProductByCategory(@PathVariable("categoryId") Long categoryId){
        return productService.findByCategory(categoryId);
    }

    @GetMapping("/search/supplier/{supplierId}")
    public List<Product> getProductBySupplier(@PathVariable("supplierId") Long supplierId){
        return productService.findBySupplier(supplierId);
    }

}
