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
@Table(name = "pay") // 指定关联的数据库的表名
public class Pay {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//strategy=GenerationType.IDENTITY 自增长
    private Long id;

    @Column(name = "payid")
    private Integer payId;
    @Column(name = "payzoom")
    private String payZoom;

    public Pay(Integer payId, String payZoom) {
        this.payId = payId;
        this.payZoom = payZoom;
    }
}
