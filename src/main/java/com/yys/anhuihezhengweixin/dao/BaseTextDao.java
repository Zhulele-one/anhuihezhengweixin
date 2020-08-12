package com.yys.anhuihezhengweixin.dao;

import com.yys.anhuihezhengweixin.entity.content.BaseText;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author zq
 */
public interface BaseTextDao extends JpaRepository<BaseText,Long> {
    /**
     * @param id textEntity id
     * @return 通过id获取所有textEntity
     */
    List<BaseText> findAllByTextEntityId(int id);
}
