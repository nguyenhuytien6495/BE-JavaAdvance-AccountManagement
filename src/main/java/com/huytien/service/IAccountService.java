package com.huytien.service;

import com.huytien.entity.Account;
import com.huytien.entity.Department;
import com.huytien.form.AccountFilterForm;
import com.huytien.form.CreatingAccountForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IAccountService extends UserDetailsService {

    public Page<Account> getAllAccounts(Pageable pageable, String search, AccountFilterForm filterForm);

    public void createAccount(CreatingAccountForm form);

    public Page<Department> getAllDepartmentsForSearch(Pageable pageable, String search);

    public boolean isAccountExistsByUserName(String username);

    public void deleteAccount(int id);

    public void deleteAccounts(List<Integer> ids);

    public Account getAccountByUsername(String username);
}
