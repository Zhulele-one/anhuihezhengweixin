package com.yys.anhuihezhengweixin.dao;

import com.yys.anhuihezhengweixin.entity.base.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordDao extends JpaRepository<Record,Long> {
}
