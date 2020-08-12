package com.yys.anhuihezhengweixin.dao;

import com.yys.anhuihezhengweixin.entity.base.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author zq
 */
public interface JobDao extends JpaRepository<Job,Long> {
    /**
     * @param id 通过 jobid 获取job
     * @return 返回job列表
     */
    List<Job> findAllByJobId(Integer id);

}
