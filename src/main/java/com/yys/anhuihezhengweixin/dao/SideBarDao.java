package com.yys.anhuihezhengweixin.dao;

import com.yys.anhuihezhengweixin.entity.base.Sidebar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SideBarDao extends JpaRepository<Sidebar,Long> {
    List<Sidebar> findAllByGroupId(int id);
}
