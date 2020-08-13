package com.yys.anhuihezhengweixin.entity.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @author zq
 * 应聘表单实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "form")
public class FormEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;


    private String name;
    private Integer age;
    private Integer sex;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date birth;
    private Integer record;
    private String email;
    private String phone;
    @Column(name = "filepath")
    private String filePath;
    private Integer job;
    private Integer pay;
    private String introduction;
    private String history;

}
