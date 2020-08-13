package com.yys.anhuihezhengweixin.entity.base;

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
@Table(name = "otherimg")
public class OtherImg {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "picid")
    private Integer picId;
    private String url;


}