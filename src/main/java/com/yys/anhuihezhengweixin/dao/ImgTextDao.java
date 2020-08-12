package com.yys.anhuihezhengweixin.dao;

import com.yys.anhuihezhengweixin.entity.content.ImgText;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author zq
 */
public interface ImgTextDao extends JpaRepository<ImgText,Long> {


    /**
     * @param textEntityId 页面对象id
     * @return 获取所有textentity 通过textEntityId
     */
    List<ImgText> findAllByTextEntityId(int textEntityId);

    /**
     * @param textEntityId 页面对象id
     * @param pageable 增加分页
     * @return 返回一个page对象
     */
    Page<ImgText> findAllByTextEntityId(int textEntityId,Pageable pageable);

    /**
     * @param textId 文章id
     * @return 返回列表
     */
    List<ImgText> findAllByTextId(int textId);
}
