package com.yys.anhuihezhengweixin.entity.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author zq
 * 页面实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "html") // 指定关联的数据库的表名
public class HTMLEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//strategy=GenerationType.IDENTITY 自增长
    private Long id;


    private String title;
    @Column(name = "titlesign")
    private Integer titleSign;

    private String alia;

    @Transient
    private TextEntity textEntity;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HTMLEntity that = (HTMLEntity) o;
        return Objects.equals(id, that.id);
    }
}
