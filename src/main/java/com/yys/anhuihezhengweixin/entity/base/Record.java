package com.yys.anhuihezhengweixin.entity.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author zq
 * 学历实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "record")
public class Record {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "recordid")
    private Integer recordId;
    @Column(name = "recordname")
    private String recordName;


}
