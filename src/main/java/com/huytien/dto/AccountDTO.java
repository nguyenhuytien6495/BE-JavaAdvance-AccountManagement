package com.huytien.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountDTO {

    private Integer id;

    private String username;

    private String fullName;

    private String role;

    private String departmentName;
}
