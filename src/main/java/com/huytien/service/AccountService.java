package com.huytien.service;

import com.huytien.entity.Account;
import com.huytien.entity.Department;
import com.huytien.form.AccountFilterForm;
import com.huytien.form.CreatingAccountForm;
import com.huytien.repository.IAccountRepository;
import com.huytien.repository.IDepartmentRepository;
import com.huytien.specification.account.AccountSpecification;
import com.huytien.specification.account.DepartmentSpecificationForSearch;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AccountService implements IAccountService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private IDepartmentRepository departmentRepository;


    @Override
    public Page<Account> getAllAccounts(Pageable pageable, String search, AccountFilterForm filterForm) {
        Specification<Account> where = AccountSpecification.buildWhere(search, filterForm);
        return accountRepository.findAll(where, pageable);
    }

    @Override
    public Page<Department> getAllDepartmentsForSearch(Pageable pageable, String search) {
        Specification<Department> where = DepartmentSpecificationForSearch.buildWhere(search);
        return departmentRepository.findAll(where, pageable);
    }

    @Override
    public void createAccount(CreatingAccountForm form) {
        // omit id field
        TypeMap<CreatingAccountForm, Account> typeMap = modelMapper.getTypeMap(CreatingAccountForm.class, Account.class);
        if (typeMap == null) { // if not already added
            // skip field
            modelMapper.addMappings(new PropertyMap<CreatingAccountForm, Account>() {
                @Override
                protected void configure() {
                    skip(destination.getId());
                }
            });
        }

        // convert form to entity
        Account account = modelMapper.map(form, Account.class);

        accountRepository.save(account);

    }

    @Override
    public boolean isAccountExistsByUserName(String username) {
        return accountRepository.existsByUsername(username);
    }

    @Override
    public void deleteAccount(int id) {
        accountRepository.deleteById(id);
    }

    @Override
    public void deleteAccounts(List<Integer> ids) {
        accountRepository.deleteByIds(ids);
    }

    @Override
    public Account getAccountByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);

        if (account == null) {
            // 401
            throw new UsernameNotFoundException("Account not found: " + username);
        }

        return new User(
                account.getUsername(),
                account.getPassword(),
                AuthorityUtils.createAuthorityList(account.getRole().toString()));
    }
}
