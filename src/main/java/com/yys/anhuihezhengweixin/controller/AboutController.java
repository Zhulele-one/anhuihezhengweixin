package com.yys.anhuihezhengweixin.controller;

import com.yys.anhuihezhengweixin.entity.base.HTMLEntity;
import com.yys.anhuihezhengweixin.entity.base.Sidebar;
import com.yys.anhuihezhengweixin.entity.base.TextEntity;
import com.yys.anhuihezhengweixin.entity.content.BaseText;
import com.yys.anhuihezhengweixin.response.ImgTextResponse;
import com.yys.anhuihezhengweixin.service.PageService;
import com.yys.anhuihezhengweixin.service.TextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.yys.anhuihezhengweixin.util.HTMLUtils.completeAjaxData;
import static com.yys.anhuihezhengweixin.util.HTMLUtils.completeData;
import static com.yys.anhuihezhengweixin.util.HTMLUtils.completeStringData;
import static com.yys.anhuihezhengweixin.util.HtmlTag.*;

/**
 * @author zq
 */
@Controller
@RequestMapping("/__about__")
public class AboutController {

    private HTMLEntity htmlEntity;
    private List<TextEntity> textEntities;
    private List<Sidebar> sidebars;

    private final PageService pageService;
    private final TextService textService;

    @Autowired
    public AboutController(PageService pageService, TextService textService) {
        this.pageService = pageService;
        this.textService = textService;
    }


    @ModelAttribute
    public void init(){
        if(textEntities == null){
            if(sidebars == null){
                sidebars = pageService.initSidebar(ABOUTID);
            }
            textEntities = pageService.initPageText(ABOUTID,sidebars);
        }
        if(htmlEntity == null){
            htmlEntity = pageService.initPage(ABOUTID);
        }

    }


    @GetMapping("/birefing")
    public ModelAndView aboutCompanyBriefing(){
        BaseText byId = textService.getBaseTextByTextEntityId(BIREFINGCONTENT);

        return completeStringData(htmlEntity,textEntities,TEXTTEMPLATE,BIREFIRETEXTENTITY,byId);
    }

    @GetMapping("/honor/{page}")
    public ModelAndView aboutHonor(@PathVariable Integer page){
        return completeData(pageService,htmlEntity,textEntities,IMGLISTTEMPLATE,HONORTEXTENTITY,HONORECONTENT,page,false);
    }

    @GetMapping("/honor/ajax/{page}")
    @ResponseBody
    public ImgTextResponse newsMessageAjax(@PathVariable Integer page){
        return completeAjaxData(pageService,page,HONORECONTENT);
    }



    @GetMapping("/achievement/{page}")
    public ModelAndView aboutAchievement(@PathVariable Integer page){
        return completeData(pageService,htmlEntity,textEntities,IMGLISTTEMPLATE,ACHIEVEMENTETEXTENTITY,ACHIEVEMENTCONTENT,page,true);
    }

    @GetMapping("/achievement/ajax/{page}")
    @ResponseBody
    public ImgTextResponse newsMessageAjaxAchievement(@PathVariable Integer page){
        return completeAjaxData(pageService,page,HONORECONTENT);
    }

    @GetMapping("/sonCompany1")
    public ModelAndView aboutSonCompany1(){
        BaseText byId = textService.getBaseTextByTextEntityId(ANHUIJUCHENGCOMPANYCONENT);

        return completeStringData(htmlEntity,textEntities,TEXTTEMPLATE,SONCOMPANYTEXTENTITY,byId);
    }

    @GetMapping("/sonCompany2")
    public ModelAndView aboutSonCompany2(){
        BaseText byId = textService.getBaseTextByTextEntityId(ANHUIHEZHENGZAOJIACOMPANYCONTENT);
        return completeStringData(htmlEntity,textEntities,TEXTTEMPLATE,SONCOMPANYTEXTENTITY,byId);
    }

    @GetMapping("/message/{id}")
    public ModelAndView aboutAchievementMessage(@PathVariable Long id){

        BaseText byId = textService.getBaseTextById(id);
        return completeStringData(htmlEntity,textEntities,TEXTTEMPLATE,ACHIEVEMENTETEXTENTITY,byId);
    }

}
