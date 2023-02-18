package com.huytien.form;

import com.huytien.entity.Account;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountFilterForm {

	private Account.Role role;

	private String departmentName;

}

