package com.yys.anhuihezhengweixin.service;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import com.yys.anhuihezhengweixin.dao.HTMLDao;
import com.yys.anhuihezhengweixin.dao.ImgTextDao;
import com.yys.anhuihezhengweixin.dao.SideBarDao;
import com.yys.anhuihezhengweixin.dao.TextDao;
import com.yys.anhuihezhengweixin.entity.base.HTMLEntity;
import com.yys.anhuihezhengweixin.entity.base.Sidebar;
import com.yys.anhuihezhengweixin.entity.base.TextEntity;
import com.yys.anhuihezhengweixin.entity.content.ImgText;
import javafx.geometry.Side;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.*;

import static com.yys.anhuihezhengweixin.util.HtmlTag.SIZE;

@Log4j2
@Service
public class PageService {

    private final HTMLDao htmlDao;
    private final TextDao textDao;
    private final SideBarDao sideBarDao;
    private final ImgTextDao imgTextDao;


    @Autowired
    public PageService(HTMLDao htmlDao, TextDao textDao, SideBarDao sideBarDao, ImgTextDao imgTextDao) {
        this.htmlDao = htmlDao;
        this.textDao = textDao;
        this.sideBarDao = sideBarDao;
        this.imgTextDao = imgTextDao;
    }

    public HTMLEntity initPage(int id) {
        Optional<HTMLEntity> byId = htmlDao.findById((long) id);
        return byId.orElseGet(() -> {
            log.error("页面数据为空,请检查数据库html表");
            return new HTMLEntity(1L,"首页",1,null,null);
        });
    }

    public List<HTMLEntity> getAllHTMLEntity(){
        List<HTMLEntity> all = htmlDao.findAll();
        if(all == null){
            log.error("没有找到html 请检查数据库html表数据");
            return null;
        }
        all.sort(Comparator.comparingInt(HTMLEntity::getTitleSign));
        return all;
    }

    public HTMLEntity getHTMLEntityById(Long id){
        Optional<HTMLEntity> byId = htmlDao.findById(id);
        return byId.orElse(null);
    }

    public List<TextEntity> initPageText(int id,List<Sidebar> sidebars){
        List<TextEntity> allByGroupId = textDao.findAllByGroupId(id);

        if(allByGroupId.isEmpty()){
            log.error("页面数据为空,请检查数据库text表");
            return Arrays.asList(
                    new TextEntity(1L,"业绩展示","company achievement",2,1,null,null,null),
                    new TextEntity(2L,"荣誉资质","company honor",2,2,null,null,null)
            );
        } else {
            allByGroupId.sort(Comparator.comparingInt(TextEntity::getSideId));

            for (TextEntity t : allByGroupId){
                t.setSidebars(sidebars);
            }
            return allByGroupId;
        }
    }


    public List<Sidebar> initSidebar(int id){
        List<Sidebar> allByGroupId = sideBarDao.findAllByGroupId(id);
        if(allByGroupId.isEmpty()){
            log.info("侧边栏数据为空");
            return new ArrayList<>();
        } else {
            ArrayList<Sidebar> sonSidebars = new ArrayList<>();
            for (Sidebar anAllByGroupId1 : allByGroupId) {
                if (anAllByGroupId1.getParentId() != 0) {
                    sonSidebars.add(anAllByGroupId1);
                }
            }
            for (Sidebar sonSidebar : sonSidebars) {
                allByGroupId.remove(sonSidebar);
                for (Sidebar anAllByGroupId : allByGroupId) {
                    if (anAllByGroupId.getId() == sonSidebar.getParentId()) {
                        if(anAllByGroupId.getNodeNames() == null){
                            anAllByGroupId.setNodeNames(new ArrayList<>());
                        }
                        anAllByGroupId.getNodeNames().add(sonSidebar);
                    }
                }
            }
            allByGroupId.sort(Comparator.comparingInt(Sidebar::getSortId));
        }
        return allByGroupId;
    }





    public Page<ImgText> getContent(int textEntityId, int pageNum){

        if(pageNum <1){
            pageNum = 1;
        }

        Sort sort = Sort.by(Sort.Direction.DESC,"id");

        Pageable pageable = PageRequest.of(pageNum - 1, SIZE,sort);

        Page<ImgText> list = imgTextDao.findAllByTextEntityId(textEntityId,pageable);

        if (list.isEmpty()) {
            log.info("内容为空");
        }
        return list;
    }

    public List<ImgText> getContent(int textEntityId){

        List<ImgText> allByTextEntityId = imgTextDao.findAllByTextEntityId(textEntityId);

        if (allByTextEntityId.isEmpty()) {
            log.info("内容为空");
        }
        return allByTextEntityId;
    }


    public ImgText saveImgText(ImgText imgText) {
        return imgTextDao.save(imgText);
    }

    public ImgText getImgTextById(int id){
        return imgTextDao.findById((long) id).orElse(null);
    }

    public ImgText getImgTextByTextId(int textId) {
        List<ImgText> allByTextId = imgTextDao.findAllByTextId(textId);
        if(allByTextId == null || allByTextId.size()<1){
            return null;
        } else {
            return allByTextId.get(0);
        }
    }

    public TextEntity getTextEntityById(Long id){
        Optional<TextEntity> byId = textDao.findById(id);
        return byId.orElse(null);
    }

    public TextEntity getTextEntityBySideId(Integer id){
        List<TextEntity> allBySideId = textDao.findAllBySideId(id);
        if(allBySideId == null || allBySideId.size() < 1){
            return null;
        }
        return allBySideId.get(0);
    }

    public List<Sidebar> findAllSidebarByGroupId(Integer id) {
        return sideBarDao.findAllByGroupId(id);
    }

    public Boolean deleteImgPage(Long id) {
        Optional<ImgText> byId = imgTextDao.findById(id);
        if (byId.isPresent()) {
            imgTextDao.delete(byId.get());
            return true;
        } else {
            log.info("没有此列表");
            return false;
        }
    }


}
