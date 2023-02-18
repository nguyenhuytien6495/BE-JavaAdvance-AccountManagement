package com.huytien.form;

import com.huytien.entity.Account;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreatingAccountForm {

	private String username;

	private String firstName;

	private String lastName;

	private Account.Role role;

	private int departmentId;

}
