package com.huytien.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class DepartmentTypeConvert implements AttributeConverter<Department.Type, String> {

    // Define from Java to DB
    @Override
    public String convertToDatabaseColumn(Department.Type type) {
        if(type == null){
            return null;
        }
        return type.getValue();
    }

    // Convert form DB to Java
    @Override
    public Department.Type convertToEntityAttribute(String sqlValue) {
        if(sqlValue == null){
            return null;
        }
        return Department.Type.toEnum(sqlValue);
    }
}
