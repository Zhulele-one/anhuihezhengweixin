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
@Table(name = "imgtext")
public class ImgText {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(name = "imgurl")
    private String imgUrl;

    /**
     * 要跳转的页面
     * */
    private String url;

    @Column(name = "abstracttext")
    private String abstractText;

    private String content;
    @Column(name = "textentityid")
    private Integer textEntityId;

    @Column(name = "textid")
    private Integer textId;

}
