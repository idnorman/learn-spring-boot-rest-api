package kelaskoding.restapi.controllers;

import kelaskoding.restapi.dto.ResponseData;
import kelaskoding.restapi.dto.SearchData;
import kelaskoding.restapi.dto.SupplierData;
import kelaskoding.restapi.entities.Supplier;
import kelaskoding.restapi.services.SupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {

    private SupplierService supplierService;
    private ModelMapper modelMapper;

    @Autowired
    public SupplierController(SupplierService supplierService, ModelMapper modelMapper){
        this.supplierService = supplierService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseData<Supplier>> create(@Valid @RequestBody SupplierData supplierData, Errors errors){
        ResponseData responseData = new ResponseData();
        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            responseData.setMessage("Data is not valid");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Supplier supplier = this.modelMapper.map(supplierData, Supplier.class);

        responseData.setStatus(true);
        responseData.setPayload(this.supplierService.save(supplier));
        responseData.setMessage("Data created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseData);
    }

    @GetMapping("/get")
    public Iterable<Supplier> findAll(){
        return this.supplierService.findAll();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseData<Supplier>> findOne(@PathVariable("id") Long id){
        ResponseData responseData = new ResponseData();
        Supplier supplier = this.supplierService.findOne(id);
        responseData.setPayload(supplier);
        responseData.setStatus(true);
        responseData.setMessage("Data retrieved successfully");
        responseData.setMessages(null);
        return supplier == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(null) : ResponseEntity.status(HttpStatus.FOUND).body(responseData);
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseData<Supplier>> update(@Valid @RequestBody SupplierData supplierData, Errors errors){
        ResponseData responseData = new ResponseData();
        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            responseData.setMessage("Data is not valid");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Supplier supplier = this.modelMapper.map(supplierData, Supplier.class);
        responseData.setStatus(true);
        responseData.setPayload(this.supplierService.save(supplier));
        responseData.setMessage("Data updated successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseData);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOne(@PathVariable("id") Long id){
        this.supplierService.deleteOne(id);
    }

    @PostMapping("/find-by-email")
    public Supplier findSupplierByEmail(@RequestBody SearchData searchData){
        return supplierService.findByEmail(searchData.getSearchKey());
    }

    @PostMapping("/find-by-name")
    public List<Supplier> findByName(@RequestBody SearchData searchData){
        return supplierService.findByName(searchData.getSearchKey());
    }

    @PostMapping("/find-by-name-suffix")
    public List<Supplier> findByNameStartsWith(@RequestBody SearchData searchData){
        return supplierService.findByNameStartsWith(searchData.getSearchKey());
    }

    @PostMapping("/find-by-name-or-email")
    public List<Supplier> findByNameOrEmail(@RequestBody SearchData searchData){
        return supplierService.findByNameContainsOrEmailContains(searchData.getSearchKey(), searchData.getOtherSearchKey());
    }

}
