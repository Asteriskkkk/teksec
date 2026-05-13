package com.example.zarabank.repository;

import com.example.zarabank.entity.CurrentAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrentAccountRepository extends JpaRepository<CurrentAccount, Long> {

    Optional<CurrentAccount> findByAccountNumber(String accountNumber);
}
