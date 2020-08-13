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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.yys.anhuihezhengweixin.util.HTMLUtils.*;
import static com.yys.anhuihezhengweixin.util.HtmlTag.*;
import static com.yys.anhuihezhengweixin.util.TextEntityUtils.getTextEntityEmpty;

@Controller
@RequestMapping("/about")
public class ZAboutController {

    private HTMLEntity htmlEntity;
    private List<TextEntity> textEntities;

    private final PageService pageService;
    private final TextService textService;

    @Autowired
    public ZAboutController(PageService pageService, TextService textService) {
        this.pageService = pageService;
        this.textService = textService;
    }


    @ModelAttribute
    public void init(){
        if(textEntities == null) {
            textEntities = pageService.initPageText(ABOUTID, null);
        }
        if(htmlEntity == null){
            htmlEntity = pageService.initPage(ABOUTID);
        }

    }


    @GetMapping("/birefing")
    public String aboutCompanyBriefing(){
        BaseText byId = textService.getBaseTextByTextEntityId(BIREFINGCONTENT);

        if(byId == null || byId.getId() == 0){
            byId = new BaseText();
            byId.setTextEntityId(BIREFINGCONTENT);
            byId.setTitle("公司简介");
            byId.setContent("公司简介为空 请指定内容");

            textService.savePage(byId);
        }

        return "redirect:/preview/editPage/" + byId.getId() + "/0";
    }

    @GetMapping("/honor/{page}")
    public ModelAndView aboutHonor(@PathVariable Integer page){
        return completeData(pageService,htmlEntity,textEntities,ZITEMLISTTEMPLATE,HONORTEXTENTITY,HONORECONTENT,page,false);
    }

    @GetMapping("/honor/ajax/{page}")
    @ResponseBody
    public ImgTextResponse newsMessageAjax(@PathVariable Integer page){
        return completeAjaxData(pageService,page,HONORECONTENT);
    }



    @GetMapping("/achievement/{page}")
    public ModelAndView aboutAchievement(@PathVariable Integer page){
        return completeData(pageService,htmlEntity,textEntities,ZITEMLISTTEMPLATE,ACHIEVEMENTETEXTENTITY,ACHIEVEMENTCONTENT,page,true);
    }

    @GetMapping("/achievement/ajax/{page}")
    @ResponseBody
    public ImgTextResponse newsMessageAjaxAchievement(@PathVariable Integer page){
        return completeAjaxData(pageService,page,HONORECONTENT);
    }

    @GetMapping("/sonCompany1")
    public String aboutSonCompany1(){
        BaseText byId = textService.getBaseTextByTextEntityId(ANHUIJUCHENGCOMPANYCONENT);

        if(byId == null || byId.getId() == 0){
            byId = new BaseText();
            byId.setTextEntityId(ANHUIJUCHENGCOMPANYCONENT);
            byId.setTitle("子公司1");
            byId.setContent("子公司简介1 为空 请指定内容");
            textService.savePage(byId);
        }

        return "redirect:/preview/editPage/" + byId.getId() + "/0";
    }

    @GetMapping("/sonCompany2")
    public String aboutSonCompany2(){
        BaseText byId = textService.getBaseTextByTextEntityId(ANHUIHEZHENGZAOJIACOMPANYCONTENT);

        if(byId == null || byId.getId() == 0){
            byId = new BaseText();
            byId.setTextEntityId(ANHUIHEZHENGZAOJIACOMPANYCONTENT);
            byId.setTitle("子公司2");
            byId.setContent("子公司简介2 为空 请指定内容");
            textService.savePage(byId);
        }

        return "redirect:/preview/editPage/" + byId.getId() + "/0";
    }

    @GetMapping("/message/{id}")
    public ModelAndView aboutAchievementMessage(@PathVariable Long id){

        BaseText byId = textService.getBaseTextById(id);
        return completeStringData(htmlEntity,textEntities,TEXTTEMPLATE,ACHIEVEMENTETEXTENTITY,byId);
    }

}
