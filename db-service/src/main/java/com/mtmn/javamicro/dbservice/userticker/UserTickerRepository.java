package com.mtmn.javamicro.dbservice.userticker;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserTickerRepository extends JpaRepository<UserTicker, Integer> {
    List<UserTicker> findByUsername(String username);
    int deleteByUsername(String username);
}
