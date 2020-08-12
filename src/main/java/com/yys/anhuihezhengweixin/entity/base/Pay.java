package com.yys.anhuihezhengweixin.entity.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * 期望薪资实体类
 * @author zq
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
// 指定关联的数据库的表名
@Table(name = "pay")
public class Pay {

    //strategy=GenerationType.IDENTITY 自增长
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "payid")
    private Integer payId;
    @Column(name = "payzoom")
    private String payZoom;

}
