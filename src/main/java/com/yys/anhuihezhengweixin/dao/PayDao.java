package com.yys.anhuihezhengweixin.dao;

import com.yys.anhuihezhengweixin.entity.base.Pay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author zq
 */
public interface PayDao extends JpaRepository<Pay,Long> {
    /**
     * @param payId xx
     * @return xx
     * 返回一个payid 只有一个
     */
    List<Pay> findAllByPayId(Integer payId);
}
