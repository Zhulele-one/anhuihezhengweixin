package com.yys.anhuihezhengweixin.dao;

import com.yys.anhuihezhengweixin.entity.base.OtherImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author zq
 */
public interface OtherImgDao extends JpaRepository<OtherImg,Long> {
    /**
     * @param id 通过picid获取pic
     * @return 返回主页信息列表
     */
    List<OtherImg> findAllByPicId(Integer id);
}
