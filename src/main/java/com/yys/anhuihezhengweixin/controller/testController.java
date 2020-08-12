package com.yys.anhuihezhengweixin.controller;

import com.yys.anhuihezhengweixin.entity.base.HTMLEntity;
import com.yys.anhuihezhengweixin.entity.base.Sidebar;
import com.yys.anhuihezhengweixin.entity.base.TextEntity;
import com.yys.anhuihezhengweixin.entity.content.ImgText;
import com.yys.anhuihezhengweixin.service.PageService;
import com.yys.anhuihezhengweixin.service.TextService;
import com.yys.anhuihezhengweixin.util.MPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.yys.anhuihezhengweixin.util.HTMLUtils.completeData;
import static com.yys.anhuihezhengweixin.util.HtmlTag.*;
import static com.yys.anhuihezhengweixin.util.TextEntityUtils.getTextEntityEmpty;
import static com.yys.anhuihezhengweixin.util.UrlUtils.completeUrl;

@Controller
public class testController {


    private HTMLEntity htmlEntity;
    private List<TextEntity> textEntities;
    private List<Sidebar> sidebars;

    private final TextService textService;

    private final PageService pageService;
    @Autowired
    public testController(PageService pageService, TextService textService) {
        this.pageService = pageService;
        this.textService = textService;
    }


    @ModelAttribute
    public void init(){
        if(textEntities == null){
            if(sidebars == null){
                sidebars = pageService.initSidebar(CULTUREID);
            }
            textEntities = pageService.initPageText(CULTUREID,sidebars);
        }
        if(htmlEntity == null){
            htmlEntity = pageService.initPage(CULTUREID);
        }

    }

    @GetMapping("/test/1")
    public ModelAndView test(){
        ModelAndView mv = new ModelAndView("2");

        TextEntity textEntity = getTextEntityEmpty(1,textEntities);

        Page<ImgText> content = pageService.getContent(17, 1);

        completeUrl(content.getContent());
        textEntity.setContent(content.getContent());



        ///只封装需要的参数
        MPage mPage = new MPage(content.getTotalElements(), content.getSize(), content.getTotalPages(), content.getNumber());
        mv.addObject("pageable",mPage);

        htmlEntity.setTextEntity(textEntity);
        mv.addObject(HTML,htmlEntity);

        return mv;
    }
}
