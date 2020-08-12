package com.yys.anhuihezhengweixin.dao;

import com.yys.anhuihezhengweixin.entity.content.ImgText;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImgTextDao extends JpaRepository<ImgText,Long> {
        List<ImgText> findAllByTextEntityId(int textEntityId);
//    List<ImgText> findAllByTextEntityId(int textEntityId);

    Page<ImgText> findAllByTextEntityId(int textEntityId,Pageable pageable);

    List<ImgText> findAllByTextId(int textId);
}
