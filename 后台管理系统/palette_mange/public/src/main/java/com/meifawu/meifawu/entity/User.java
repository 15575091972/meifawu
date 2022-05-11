package com.meifawu.meifawu.entity;

import lombok.Data;

@Data
public class User {
    private int user_id;
    private String nick_name;
    private String header_url;
    private String user_category;
    private float user_balance;
}
