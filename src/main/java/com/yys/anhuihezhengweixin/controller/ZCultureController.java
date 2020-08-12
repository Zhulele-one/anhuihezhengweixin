package com.yys.anhuihezhengweixin.controller;

import com.yys.anhuihezhengweixin.entity.base.HTMLEntity;
import com.yys.anhuihezhengweixin.entity.base.Sidebar;
import com.yys.anhuihezhengweixin.entity.base.TextEntity;
import com.yys.anhuihezhengweixin.entity.content.BaseText;
import com.yys.anhuihezhengweixin.entity.content.ImgText;
import com.yys.anhuihezhengweixin.response.ImgTextResponse;
import com.yys.anhuihezhengweixin.response.SimpleResponse;
import com.yys.anhuihezhengweixin.service.PageService;
import com.yys.anhuihezhengweixin.service.TextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.yys.anhuihezhengweixin.util.HTMLUtils.*;
import static com.yys.anhuihezhengweixin.util.HtmlTag.*;
import static com.yys.anhuihezhengweixin.util.TextEntityUtils.getTextEntityEmpty;
import static com.yys.anhuihezhengweixin.util.UrlUtils.completeUrl;

@Controller
@RequestMapping("/culture")
public class ZCultureController {


    private HTMLEntity htmlEntity;
    private List<TextEntity> textEntities;

    private final TextService textService;

    private final PageService pageService;
    @Autowired
    public ZCultureController(PageService pageService, TextService textService) {
        this.pageService = pageService;
        this.textService = textService;
    }


    @ModelAttribute
    public void init(){
        if(textEntities == null) {
            textEntities = pageService.initPageText(CULTUREID, null);
        }
        if(htmlEntity == null){
            htmlEntity = pageService.initPage(CULTUREID);
        }

    }


    @GetMapping("/{page}")
    public ModelAndView culture(@PathVariable Integer page){
        return completeData(pageService,htmlEntity,textEntities,ZITEMLISTTEMPLATE,CULTUREEXTENTITY,CULTURECONTENT,page,true);
//        mv.addObject("sideMap",completeSideBar(pageService));

    }


    @GetMapping("/message/{id}")
    public ModelAndView newsMessage(@PathVariable Long id){
        BaseText byId = textService.getBaseTextById(id);
        return completeStringData(htmlEntity,textEntities,TEXTTEMPLATE,CULTUREEXTENTITY,byId);
    }


    @GetMapping("/ajax/{page}")
    @ResponseBody
    public ImgTextResponse newsMessageAjax(@PathVariable Integer page){
        return completeAjaxData(pageService,page,CULTURECONTENT);
    }



}
