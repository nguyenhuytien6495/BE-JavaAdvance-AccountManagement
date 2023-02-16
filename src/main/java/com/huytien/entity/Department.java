package com.huytien.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "Department")
@NoArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "`name`", length = 50, nullable = false, unique = true, updatable = false)
    private String name;

    @Column(name = "total_member")
    private Integer totalMember;

    @Column(name = "`type`", nullable = false)
    @Convert(converter = DepartmentTypeConvert.class)
    private Type type;

    @Column(name = "created_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createdDate;

    @OneToMany(mappedBy = "department")
    private List<Account> accounts;

    public enum Type{
        DEV("Dev"), TEST("Test"), SCRUM_MASTER("ScrumMaster"), PM("PM");

        private String value;

        private Type(String value){
            this.value = value;
        }

        public String getValue(){
            return value;
        }

        // define method convert value form DB to Java
        public static Type toEnum(String sqlValue){
            for(Type type: Type.values()){
                if(type.getValue().equals(sqlValue)){
                    return type;
                }
            }
            return null;
        }

    }
}
