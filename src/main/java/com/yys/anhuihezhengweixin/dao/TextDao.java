package com.yys.anhuihezhengweixin.dao;

import com.yys.anhuihezhengweixin.entity.base.TextEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author zq
 */
public interface TextDao extends JpaRepository<TextEntity,Long> {
    /**
     * @param id xx
     * @return xxx
     */
    List<TextEntity> findAllByGroupId(int id);

    /**
     * @param id xx
     * @return xxx
     */
    List<TextEntity> findAllBySideId(int id);

}
