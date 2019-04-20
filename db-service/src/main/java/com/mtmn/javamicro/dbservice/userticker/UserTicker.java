package com.mtmn.javamicro.dbservice.userticker;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name="tbl_user_ticker", schema="micro")
public class UserTicker {

    @Id
    @Column(name = "id_user_ticker")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="nm_user_name")
    @NonNull
    private String username;

    @Column(name="vl_ticker")
    @NonNull
    private String ticker;
}
