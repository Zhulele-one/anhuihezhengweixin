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
@RequestMapping("/__rule__")
public class RuleController {


    private HTMLEntity htmlEntity;
    private List<TextEntity> textEntities;
    private List<Sidebar> sidebars;


    private final PageService pageService;

    private final TextService textService;

    @Autowired
    public RuleController(PageService pageService, TextService textService) {
        this.pageService = pageService;
        this.textService = textService;
    }


    @ModelAttribute
    public void init(){
        if(textEntities == null){
            if(sidebars == null){
                sidebars = pageService.initSidebar(NEWSID);
            }
            textEntities = pageService.initPageText(NEWSID,sidebars);
        }
        if(htmlEntity == null){
            htmlEntity = pageService.initPage(NEWSID);
        }
    }


    @GetMapping("/")
    public ModelAndView aboutRule(){
        return completeData(pageService,htmlEntity,textEntities,IMGLISTTEMPLATE,RULETEXTENTITY,RULELISTCONTENT,false);
    }


    @GetMapping("/{itemId}/{page}")
    public ModelAndView aboutRuleList(@PathVariable("itemId") int itemId,@PathVariable("page") Integer page){
        return completeData(pageService,htmlEntity,textEntities,ITEMLISTTEMPLATE,RULETEXTENTITY,itemId,page,true);
    }
    @GetMapping("/{itemId}/ajax/{page}")
    @ResponseBody
    public ImgTextResponse newsMessageAjaxRule(@PathVariable("itemId") int itemId,@PathVariable Integer page){
        return completeAjaxData(pageService,page,itemId);
    }



    @GetMapping("/message/{pageId}/{textId}")
    public ModelAndView newsMessage(@PathVariable("pageId") int pageId,@PathVariable("textId") Long textId){
        BaseText byId = textService.getBaseTextById(textId);
        return completeStringData(htmlEntity,textEntities,TEXTTEMPLATE,pageId,byId);
    }




}
