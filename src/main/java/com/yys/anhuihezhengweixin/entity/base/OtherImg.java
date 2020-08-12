package com.yys.anhuihezhengweixin.entity.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "otherimg") // 指定关联的数据库的表名
public class OtherImg {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//strategy=GenerationType.IDENTITY 自增长
    private Long id;

    @Column(name = "picid")
    private Integer picId;
    private String url;

    public OtherImg(Integer picId, String url) {
        this.picId = picId;
        this.url = url;
    }
}