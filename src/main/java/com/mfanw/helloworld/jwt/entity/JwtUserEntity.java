package com.mfanw.helloworld.jwt.entity;

import lombok.Data;

@Data
public class JwtUserEntity {

    private Integer id;
    private String username;
    private String password;
    private String address;
    private int sex;

}
