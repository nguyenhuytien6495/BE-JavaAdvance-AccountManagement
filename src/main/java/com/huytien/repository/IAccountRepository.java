package com.huytien.repository;

import com.huytien.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Integer>, JpaSpecificationExecutor<Account> {

    boolean existsByUsername(String username);

    @Modifying
    @Transactional
    @Query("DELETE FROM Account WHERE id IN(:ids)")
    void deleteByIds(@Param("ids") List<Integer> ids);

    Account findByUsername(String username);
}
