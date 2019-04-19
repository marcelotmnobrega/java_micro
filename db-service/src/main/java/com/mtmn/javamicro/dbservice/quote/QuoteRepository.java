package com.mtmn.javamicro.dbservice.quote;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuoteRepository extends JpaRepository<Quote, Integer> {
    List<Quote> findByUsername(String username);
    int deleteByUsername(String username);
}
