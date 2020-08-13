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

@Controller
@RequestMapping("/news")
public class ZNewsController {


    private HTMLEntity htmlEntity;
    private List<TextEntity> textEntities;


    private final PageService pageService;

    private final TextService textService;

    @Autowired
    public ZNewsController(PageService pageService, TextService textService) {
        this.pageService = pageService;
        this.textService = textService;
    }


    @ModelAttribute
    public void init(){
        if(textEntities == null){
            textEntities = pageService.initPageText(NEWSID,null);
        }
        if(htmlEntity == null){
            htmlEntity = pageService.initPage(NEWSID);
        }
    }


    @GetMapping("/learning/{page}")
    public ModelAndView aboutLearning(@PathVariable Integer page){
        return completeData(pageService,htmlEntity,textEntities,ZITEMLISTTEMPLATE,
                LEARNINGTEXTENTITY,LEARNINGCONTENT,page,true);
    }
    @GetMapping("/learning/ajax/{page}")
    @ResponseBody
    public ImgTextResponse newsMessageAjaxLearning(@PathVariable Integer page){
        return completeAjaxData(pageService,page,LEARNINGCONTENT);
    }


    @GetMapping("/companyNews/{page}")
    public ModelAndView newsCompanyNews(@PathVariable Integer page){
        return completeData(pageService,htmlEntity,textEntities,ZITEMLISTTEMPLATE,
                COMPANYNEWSTEXTENTITY,COMPANYNEWSCONETNT,page,true);
    }
    @GetMapping("/companyNews/ajax/{page}")
    @ResponseBody
    public ImgTextResponse newsMessageAjaxCompanyNews(@PathVariable Integer page){
        return completeAjaxData(pageService,page,COMPANYNEWSCONETNT);
    }

    @GetMapping("/hangyeNews/{page}")
    public ModelAndView newsHangyeNews(@PathVariable Integer page){
        return completeData(pageService,htmlEntity,textEntities,ZITEMLISTTEMPLATE,
                HANGYENEWSTEXTENTITY,HANGYENEWSCONETNT,page,true);
    }
    @GetMapping("/hangyeNews/ajax/{page}")
    @ResponseBody
    public ImgTextResponse newsMessageAjaxHangYeNews(@PathVariable Integer page){
        return completeAjaxData(pageService,page,HANGYENEWSCONETNT);
    }

    @GetMapping("/rule")
    public ModelAndView aboutRule(){
        return completeData(pageService,htmlEntity,textEntities,ZITEMLISTTEMPLATE,RULETEXTENTITY,RULELISTCONTENT,false);
    }


    @GetMapping("/rule/{itemId}/{page}")
    public ModelAndView aboutRuleList(@PathVariable("itemId") int itemId,@PathVariable("page") Integer page){
        if(itemId == COUNTRYRULECONTENT){
            return completeData(pageService,htmlEntity,textEntities,ZITEMLISTTEMPLATE,COUNTRYRULETEXTENTITY,itemId,page,true);
        }
        if(itemId == DIFANGRULRULECONTENT){
            return completeData(pageService,htmlEntity,textEntities,ZITEMLISTTEMPLATE,DIFANGRULETEXTENTITY,itemId,page,true);
        }
        return completeData(pageService,htmlEntity,textEntities,ZITEMLISTTEMPLATE,RULETEXTENTITY,itemId,page,true);
    }

    @GetMapping("/rule/{itemId}/ajax/{page}")
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
