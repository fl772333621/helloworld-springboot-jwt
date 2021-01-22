package com.mfanw.helloworld.jwt.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author mengwei
 */
@Getter
@Setter
@ToString
public class UserDto {

    private String userId;

    private String username;

    private String password;

    private String token;
}
