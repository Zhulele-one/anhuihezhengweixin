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
@Table(name = "imgtext") // 指定关联的数据库的表名
public class ImgText {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//strategy=GenerationType.IDENTITY 自增长
    private Long id;

    private String name;  //标题
    @Column(name = "imgurl")
    private String imgUrl;
    private String url;  //要跳转的页面
    @Column(name = "abstracttext")
    private String abstractText; //摘要
    private String content; //主要内容
    @Column(name = "textentityid")
    private Integer textEntityId;

    @Column(name = "textid")
    private Integer textId;

    public ImgText(String name, String imgUrl) {
        this.name = name;
        this.imgUrl = imgUrl;
    }

    public ImgText(String name, String imgUrl, String url) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.url = url;
    }

    public ImgText(String name, String url, String abstractText, String content) {
        this.name = name;
        this.url = url;
        this.abstractText = abstractText;
        this.content = content;
    }

    public ImgText(String name, String imgUrl, String url, String abstractText, String content) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.url = url;
        this.abstractText = abstractText;
        this.content = content;
    }
}
