package com.huytien.controller;

import com.huytien.dto.AccountDTO;
import com.huytien.dto.DepartmentDTO;
import com.huytien.entity.Account;
import com.huytien.entity.Department;
import com.huytien.form.AccountFilterForm;
import com.huytien.form.CreatingAccountForm;
import com.huytien.service.AccountService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/accounts")
//@CrossOrigin(value = "*")
public class AccountController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AccountService service;

    @GetMapping()
    public Page<AccountDTO> getAllAccounts(Pageable pageable,
                                           @RequestParam(value = "search", required = false) String search, AccountFilterForm filterForm) {

        Page<Account> entityPages = service.getAllAccounts(pageable, search, filterForm);

        // convert entities --> dtos
        List<AccountDTO> dtos = modelMapper.map(entityPages.getContent(), new TypeToken<List<AccountDTO>>() {
        }.getType());

        Page<AccountDTO> dtoPages = new PageImpl<>(dtos, pageable, entityPages.getTotalElements());

        return dtoPages;

    }

    @GetMapping("/departments")
    public Page<DepartmentDTO> getAllDepartmentsForSearch(Pageable pageable,
                                                          @RequestParam(value = "search", required = false) String search) {

        Page<Department> entityPages = service.getAllDepartmentsForSearch(pageable, search);

        // convert entities --> dtos
        List<DepartmentDTO> dtos = modelMapper.map(entityPages.getContent(), new TypeToken<List<DepartmentDTO>>() {
        }.getType());

        Page<DepartmentDTO> dtoPages = new PageImpl<>(dtos, pageable, entityPages.getTotalElements());

        return dtoPages;

    }

    @PostMapping()
    public void createAccount(@RequestBody CreatingAccountForm form) {
        service.createAccount(form);
    }

    @GetMapping(value = "/username/{username}/exists")
    public boolean existsByName(@PathVariable(name = "username") String username) {
        return service.isAccountExistsByUserName(username);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteAccount(@PathVariable(name = "id") int id) {
        service.deleteAccount(id);
    }

    @DeleteMapping
    public void deleteAccounts(@RequestParam(name = "ids") List<Integer> ids) {
        service.deleteAccounts(ids);
    }
}
