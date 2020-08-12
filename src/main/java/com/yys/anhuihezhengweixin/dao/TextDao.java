package com.yys.anhuihezhengweixin.dao;

import com.yys.anhuihezhengweixin.entity.base.TextEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TextDao extends JpaRepository<TextEntity,Long> {
    List<TextEntity> findAllByGroupId(int id);

    List<TextEntity> findAllBySideId(int id);

}
