package com.yys.anhuihezhengweixin.dao;

import com.yys.anhuihezhengweixin.entity.content.BaseText;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BaseTextDao extends JpaRepository<BaseText,Long> {
    List<BaseText> findAllByTextEntityId(int id);
}
