package com.yys.anhuihezhengweixin.entity.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author zq
 * 应聘工作类型实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "job") // 指定关联的数据库的表名
public class Job {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//strategy=GenerationType.IDENTITY 自增长
    private Long id;

    @Column(name = "jobid")
    private Integer jobId;
    @Column(name = "jobname")
    private String jobName;

    public Job(Integer jobId, String jobName) {
        this.jobId = jobId;
        this.jobName = jobName;
    }
}
