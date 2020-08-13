package com.yys.anhuihezhengweixin.entity.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Comparator;
import java.util.List;

/**
 * @author zq
 * 头部信息实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "text")
public class TextEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String alia;

    @Column(name = "groupid")
    private Integer groupId;

    @Column(name = "sideid")
    private Integer sideId;

    @Column(name = "abstracttext")
    private String abstractText;

    @Transient
    private List<Sidebar> sidebars;
    @Transient
    private Object content;



}
