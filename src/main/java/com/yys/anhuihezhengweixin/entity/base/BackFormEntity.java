package com.yys.anhuihezhengweixin.entity.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;


/**
 * @author zq
 * 回馈表单实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "backform") // 指定关联的数据库的表名
public class BackFormEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//strategy=GenerationType.IDENTITY 自增长
    private Long id;

    private String name;
    private String phone;
    @Column(name = "backtype")
    private Integer backType;
    @Column(name = "backcontent")
    private String backContent;

    public BackFormEntity(String name, String phone, Integer backType, String backContent) {
        this.name = name;
        this.phone = phone;
        this.backType = backType;
        this.backContent = backContent;
    }
}
