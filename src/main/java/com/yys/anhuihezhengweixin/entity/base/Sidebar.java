package com.yys.anhuihezhengweixin.entity.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

/**
 * @author zq
 * 侧边栏实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "sidebar")
public class Sidebar{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String url;
    private String icon;
    @Column(name = "groupid")
    private int groupId;
    @Column(name = "parentid")
    private int parentId;
    @Column(name = "sortid")
    private int sortId;
    //这个字段不映射
    @Transient
    private List<Sidebar> nodeNames;


}
