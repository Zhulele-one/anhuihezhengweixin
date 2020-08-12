package com.yys.anhuihezhengweixin.entity.content;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;


/**
 * @author zq
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "basetext") // 指定关联的数据库的表名
public class BaseText {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//strategy=GenerationType.IDENTITY 自增长
    private Long id;

    //链接页面id
    @Column(name = "textentityid")
    private int textEntityId;

    private String content;

    private String title;
}
