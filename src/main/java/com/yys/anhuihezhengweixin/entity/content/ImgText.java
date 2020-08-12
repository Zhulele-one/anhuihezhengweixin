package com.yys.anhuihezhengweixin.entity.content;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@ToString
// 指定关联的数据库的表名
@Table(name = "imgtext")
public class ImgText {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//strategy=GenerationType.IDENTITY 自增长
    private Long id;
    //标题
    private String name;
    @Column(name = "imgurl")
    private String imgUrl;
    //要跳转的页面
    private String url;
    //摘要
    @Column(name = "abstracttext")
    private String abstractText;
    //主要内容
    private String content;
    @Column(name = "textentityid")
    private Integer textEntityId;

    @Column(name = "textid")
    private Integer textId;

}
