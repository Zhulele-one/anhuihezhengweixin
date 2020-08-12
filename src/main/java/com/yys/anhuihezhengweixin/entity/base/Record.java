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
@Table(name = "record") // 指定关联的数据库的表名
public class Record {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//strategy=GenerationType.IDENTITY 自增长
    private Long id;

    @Column(name = "recordid")
    private Integer recordId;
    @Column(name = "recordname")
    private String recordName;

    public Record(Integer recordId, String recordName) {
        this.recordId = recordId;
        this.recordName = recordName;
    }
}
