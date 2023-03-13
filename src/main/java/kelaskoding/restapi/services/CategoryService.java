package kelaskoding.restapi.services;

import kelaskoding.restapi.entities.Category;
import kelaskoding.restapi.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public Category save(Category category){
        return this.categoryRepository.save(category);
    }

    public Category findOne(Long id){
        Optional<Category> category = this.categoryRepository.findById(id);
        return category.isPresent() ? category.get() : null;
    }

    public Iterable<Category> findAll(){
        return this.categoryRepository.findAll();
    }

    public void deleteOne(Long id){
        this.categoryRepository.deleteById(id);
    }

    public Iterable<Category> findByName(String name, Pageable pageable){
        return categoryRepository.findByNameContains(name, pageable);
    }

    public Iterable<Category> batchSave(Iterable<Category> categories){
        return categoryRepository.saveAll(categories);
    }

}
