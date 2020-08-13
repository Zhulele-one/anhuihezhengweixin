package com.yys.anhuihezhengweixin.dao;

import com.yys.anhuihezhengweixin.entity.base.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author zq
 */
public interface RecordDao extends JpaRepository<Record,Long> {
    /**
     * @param recordId xxx
     * @return xxx
     * 只有一个
     */
    List<Record> findAllByRecordId(Integer recordId);
}
