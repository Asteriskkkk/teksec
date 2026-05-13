package com.example.zarabank.repository;

import com.example.zarabank.entity.AtmCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AtmCardRepository extends JpaRepository<AtmCard, Long> {

    Optional<AtmCard> findByCardNumber(String cardNumber);

    boolean existsByAccountId(Long accountId);
}
