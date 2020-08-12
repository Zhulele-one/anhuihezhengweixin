package com.yys.anhuihezhengweixin.dao;

import com.yys.anhuihezhengweixin.entity.base.OtherImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OtherImgDao extends JpaRepository<OtherImg,Long> {
    List<OtherImg> findAllByPicId(Integer id);
}
