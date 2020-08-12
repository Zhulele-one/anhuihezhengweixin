package com.yys.anhuihezhengweixin.dao;

import com.yys.anhuihezhengweixin.entity.base.Sidebar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author zq
 */
public interface SideBarDao extends JpaRepository<Sidebar,Long> {
    /**
     * @param id 通过groupid过去sidebar
     * @return 返回sidebar列表
     */
    List<Sidebar> findAllByGroupId(int id);
}
