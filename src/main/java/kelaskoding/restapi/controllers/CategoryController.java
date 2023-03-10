package kelaskoding.restapi.controllers;

import kelaskoding.restapi.dto.CategoryData;
import kelaskoding.restapi.dto.ResponseData;
import kelaskoding.restapi.dto.SearchData;
import kelaskoding.restapi.entities.Category;
import kelaskoding.restapi.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private CategoryService categoryService;
    private ModelMapper modelMapper;

    @Autowired
    public CategoryController(CategoryService categoryService, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("create")
    public ResponseEntity<ResponseData<Category>> create(@Valid @RequestBody CategoryData categoryData, Errors errors){
        ResponseData responseData = new ResponseData();
        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setMessage("Data is not valid");
            responseData.setPayload(null);

            return ResponseEntity.badRequest().body(responseData);
        }
        Category category = this.modelMapper.map(categoryData, Category.class);
        responseData.setStatus(true);
        responseData.setMessage("Data created successfully");
        responseData.setMessages(null);
        responseData.setPayload(categoryService.save(category));

        return ResponseEntity.status(HttpStatus.CREATED).body(responseData);
    }

    @GetMapping("/get")
    public ResponseEntity<ResponseData<Iterable<Category>>> findOne(){
        Iterable<Category> category = this.categoryService.findAll();

        ResponseData responseData = new ResponseData<>();

        responseData.setStatus(true);
        responseData.setMessage("Data retrieved successfully");
        responseData.setMessages(null);
        responseData.setPayload(category);

        return category == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(responseData);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseData<Category>> findOne(@PathVariable("id") Long id){
        Category category = this.categoryService.findOne(id);
        ResponseData responseData = new ResponseData();

        responseData.setStatus(true);
        responseData.setMessage("Data retrieved successfully");
        responseData.setMessages(null);
        responseData.setPayload(category);

        return category == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(responseData);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseData<Category>> update(@Valid @RequestBody CategoryData categoryData, Errors errors){
        ResponseData<Category> responseData = new ResponseData();
        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setMessage("Data is not valid");
            responseData.setPayload(null);

            return ResponseEntity.badRequest().body(responseData);
        }
        Category category = this.modelMapper.map(categoryData, Category.class);
        responseData.setStatus(true);
        responseData.setMessage("Data updated successfully");
        responseData.setMessages(null);
        responseData.setPayload(categoryService.save(category));

        return ResponseEntity.status(HttpStatus.CREATED).body(responseData);
    }

    @DeleteMapping("delete/{id}")
    public void deleteOne(@PathVariable("id") Long id){
        this.categoryService.deleteOne(id);
    }

    @PostMapping("/search/{size}/{page}")
    public Iterable<Category> findByName(@RequestBody SearchData searchData, @PathVariable("size") int size, @PathVariable("page") int page){
        Pageable pageable = PageRequest.of(page, size);
        return categoryService.findByName(searchData.getSearchKey(), pageable);
    }

    @PostMapping("/search/{size}/{page}/{sort}")
    public Iterable<Category> findByName(@RequestBody SearchData searchData, @PathVariable("size") int size, @PathVariable("page") int page, @PathVariable("sort") String sort){
//        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
//        if(sort.equalsIgnoreCase("desc")){
//            pageable = PageRequest.of(page, size, Sort.by("id").descending());
//        }

        Pageable pageable = sort.equalsIgnoreCase("desc") ? PageRequest.of(page, size, Sort.by("id")) : PageRequest.of(page, size, Sort.by("id").descending());

        return categoryService.findByName(searchData.getSearchKey(), pageable);
    }
    @PostMapping("/batch")
    public ResponseEntity<ResponseData<Iterable<Category>>> batchCreate(@RequestBody Category[] categories){
        ResponseData responseData = new ResponseData();
        responseData.setPayload(categoryService.batchSave(Arrays.asList(categories)));
        responseData.setStatus(true);
        responseData.setMessage("Data saved");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseData);
    }
}
