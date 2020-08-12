package com.yys.anhuihezhengweixin.service;

import com.yys.anhuihezhengweixin.dao.HTMLDao;
import com.yys.anhuihezhengweixin.dao.OtherImgDao;
import com.yys.anhuihezhengweixin.dao.SideBarDao;
import com.yys.anhuihezhengweixin.dao.TextDao;
import com.yys.anhuihezhengweixin.entity.base.HTMLEntity;
import com.yys.anhuihezhengweixin.entity.base.OtherImg;
import com.yys.anhuihezhengweixin.entity.base.Sidebar;
import com.yys.anhuihezhengweixin.entity.base.TextEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Service
public class EditService {

    private final SideBarDao sideBarDao;
    private final TextDao textDao;

    private final HTMLDao htmlDao;

    private final OtherImgDao otherImgDao;

    @Autowired
    public EditService(SideBarDao sideBarDao, TextDao textDao, HTMLDao htmlDao, OtherImgDao otherImgDao) {
        this.sideBarDao = sideBarDao;
        this.textDao = textDao;
        this.htmlDao = htmlDao;
        this.otherImgDao = otherImgDao;
    }

    public Sidebar editSidebar(String name,Long id){
        Optional<Sidebar> byId = sideBarDao.findById(id);
        System.out.println(name + id);
        if (byId.isPresent()){
            Sidebar sidebar = byId.get();
            sidebar.setTitle(name);
            return sideBarDao.save(sidebar);
        }else {
            return null;
        }

    }

    public TextEntity editTop(String name,Long id){
        Optional<TextEntity> byId = textDao.findById(id);
        System.out.println(name + id);
        if (byId.isPresent()){
            TextEntity textEntity = byId.get();
            textEntity.setTitle(name);
            return textDao.save(textEntity);
        }else {
            return null;
        }

    }

    public HTMLEntity editHTML(String name, Long id) {
        Optional<HTMLEntity> byId = htmlDao.findById(id);
        System.out.println(name + id);
        if (byId.isPresent()){
            HTMLEntity htmlEntity = byId.get();
            htmlEntity.setTitle(name);
            return htmlDao.save(htmlEntity);
        }else {
            return null;
        }
    }

    public TextEntity editTop1(String name,Long id){
        Optional<TextEntity> byId = textDao.findById(id);
        System.out.println(name + id);
        if (byId.isPresent()){
            TextEntity textEntity = byId.get();
            textEntity.setAlia(name);
            return textDao.save(textEntity);
        }else {
            return null;
        }
    }

    public OtherImg saveOtherImg(OtherImg otherImg){
        return otherImgDao.save(otherImg);
    }

    public OtherImg findOtherImgById(Long id){
        return otherImgDao.findById(id).orElse(null);
    }

    public OtherImg findOtherImgByPicId(Integer picId){
        List<OtherImg> allByPicId = otherImgDao.findAllByPicId(picId);
        if(allByPicId == null || allByPicId.size()<1 ){
            return null;
        } else {
            return allByPicId.get(0);
        }
    }

    public List<OtherImg> findAllOtherImg(){
        return otherImgDao.findAll();
    }
}
