package com.yys.anhuihezhengweixin.dao;

import com.yys.anhuihezhengweixin.entity.base.Pay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayDao extends JpaRepository<Pay,Long> {
}
