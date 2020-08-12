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
@Table(name = "sidebar") // 指定关联的数据库的表名
public class Sidebar{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//strategy=GenerationType.IDENTITY 自增长
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

    public Sidebar(String title, String url, List<Sidebar> nodeNames) {
        this.title = title;
        this.url = url;
        this.nodeNames = nodeNames;
    }
}
