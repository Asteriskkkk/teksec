package com.example.zarabank.repository;

import com.example.zarabank.entity.SavingsAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, Long> {

    Optional<SavingsAccount> findByAccountNumber(String accountNumber);
}
