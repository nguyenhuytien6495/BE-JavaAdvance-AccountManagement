package com.huytien.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
public class DepartmentDTO {

    private Integer id;

    private String name;


}
