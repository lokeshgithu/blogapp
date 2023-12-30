package com.blog.controller;

import com.blog.entity.Employer;
import com.blog.payload.EmployerDto;
import com.blog.service.impl.EmployerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/employer")
public class EmployerController {

    @Autowired
    private EmployerServiceImpl employerServiceimpl;

    //http://localhost:8080/api/employer?pageNo=0&pageSize=5&sortBy=name&sortDir=asc
    @GetMapping
    public List<Employer> getAllEmployer(
            @RequestParam(name = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "3", required = false) int pageSize,
            @RequestParam(name = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(name = "sortDir", defaultValue = "asc", required = false) String sortDir


    ) {
        List<Employer> employers = employerServiceimpl.GetAllEmployer(pageNo, pageSize, sortBy, sortDir);
        return employers;
    }

    @PostMapping
    public ResponseEntity<?> storeData(@Valid @RequestBody EmployerDto employerDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        employerServiceimpl.storeData(employerDto);
        return new ResponseEntity<>("Data is sava", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> Delete(@PathVariable long id) {
        employerServiceimpl.deleteRecord(id);
        return new ResponseEntity<>("Delete Record", HttpStatus.OK);
    }
}

