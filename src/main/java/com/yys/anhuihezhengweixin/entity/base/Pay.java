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
@Table(name = "pay")
public class Pay {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "payid")
    private Integer payId;
    @Column(name = "payzoom")
    private String payZoom;

}
