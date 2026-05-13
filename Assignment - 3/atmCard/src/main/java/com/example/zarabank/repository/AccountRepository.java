package com.example.zarabank.repository;

import com.example.zarabank.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByAccountNumber(String accountNumber);

    @Query("SELECT a FROM Account a WHERE a.atmCard.cardType = :cardType")
    List<Account> findAccountsByAtmCardType(@Param("cardType") com.example.zarabank.entity.AtmCard.CardType cardType);

    boolean existsByAccountNumber(String accountNumber);
}
