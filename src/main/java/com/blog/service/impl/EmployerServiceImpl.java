package com.blog.service.impl;

import com.blog.entity.Employer;
import com.blog.exception.EmNotFoundException;
import com.blog.payload.EmployerDto;
import com.blog.repository.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployerServiceImpl {
    @Autowired
    private EmployerRepository employerRepo;

    public void storeData(EmployerDto employerDto) {
        Employer employer = new Employer();
        employer.setName(employerDto.getName());
        employer.setEmail(employerDto.getEmail());
        employer.setMobile(employerDto.getMobile());

        employerRepo.save(employer);
    }

    public void deleteRecord(long id) {
        employerRepo.findById(id).orElseThrow(
                ()-> new EmNotFoundException("id number is not found"+id)
        );
        employerRepo.deleteById(id);
    }

    public List<Employer> GetAllEmployer(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Employer> all = employerRepo.findAll(pageable);
        List<Employer> content = all.getContent();
        return content;
    }
}
