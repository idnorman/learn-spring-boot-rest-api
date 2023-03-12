package kelaskoding.restapi.services;

import kelaskoding.restapi.entities.Supplier;
import kelaskoding.restapi.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SupplierService {

    private SupplierRepository supplierRepository;

    @Autowired
    public void setSupplierRepository(SupplierRepository supplierRepository){
        this.supplierRepository = supplierRepository;
    }

    public Supplier save(Supplier supplier){
        return supplierRepository.save(supplier);
    }
    public Supplier findOne(Long id){
        Optional<Supplier> supplier = supplierRepository.findById(id);
        return supplier.isPresent() ? supplier.get() : null;
    }
    public Iterable<Supplier> findAll(){
        return supplierRepository.findAll();
    }
    public void deleteOne(Long id){
        supplierRepository.deleteById(id);
    }
    public Supplier findByEmail(String email){
        return supplierRepository.findByEmail(email);
    }
    public List<Supplier> findByName(String name){
        return supplierRepository.findByNameContains(name);
    }
    public List<Supplier> findByNameStartsWith(String prefix){
        return supplierRepository.findByNameStartsWith(prefix);
    }
    public List<Supplier> findByNameContainsOrEmailContains(String name, String email){
        return supplierRepository.findByNameContainsOrEmailContains(name, email);
    }
}
