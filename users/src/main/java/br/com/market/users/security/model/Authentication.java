package br.com.market.users.security.model;

import lombok.Data;

@Data
public class Authentication {
    private String login;
    private String password;
    private String token;
}
