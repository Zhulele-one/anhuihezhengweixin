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

import static com.yys.anhuihezhengweixin.util.HTMLUtils.*;
import static com.yys.anhuihezhengweixin.util.HtmlTag.*;

@Controller
@RequestMapping("/business/")
public class ZBusinessController {

    private HTMLEntity htmlEntity;
    private List<TextEntity> textEntities;

    private final PageService pageService;

    private final TextService textService;

    @Autowired
    public ZBusinessController(PageService pageService, TextService textService) {
        this.pageService = pageService;
        this.textService = textService;
    }


    @ModelAttribute
    public void init(){
        if(textEntities == null) {
            textEntities = pageService.initPageText(BUSINESSID, null);
        }
        if(htmlEntity == null){
            htmlEntity = pageService.initPage(BUSINESSID);
        }

    }

    @GetMapping("/proxy/{page}")
    public ModelAndView businessProxy(@PathVariable Integer page){
        return completeData(pageService,htmlEntity,textEntities,ZITEMLISTTEMPLATE,PROXYTEXTENTITY,PROXYCONTENT,page,true);
    }

    @GetMapping("/proxy/ajax/{page}")
    @ResponseBody
    public ImgTextResponse newsMessageAjaxProxy(@PathVariable Integer page){
        return completeAjaxData(pageService,page,PROXYCONTENT);
    }

    @GetMapping("/supervision/{page}")
    public ModelAndView businessSupervision(@PathVariable Integer page){
        return completeData(pageService,htmlEntity,textEntities,ZITEMLISTTEMPLATE,SUPERVISIONTEXTENTITY,SUPERVISIONCONTENT,page,true);
    }

    @GetMapping("/supervision/ajax/{page}")
    @ResponseBody
    public ImgTextResponse newsMessageAjaxSupervision(@PathVariable Integer page){
        return completeAjaxData(pageService,page,SUPERVISIONCONTENT);
    }

    @GetMapping("/cost/{page}")
    public ModelAndView businessCost(@PathVariable Integer page){
        return completeData(pageService,htmlEntity,textEntities,ZITEMLISTTEMPLATE,COSTTEXTENTITY,COSTCONTENT,page,true);
    }
    @GetMapping("/cost/ajax/{page}")
    @ResponseBody
    public ImgTextResponse newsMessageAjaxCost(@PathVariable Integer page){
        return completeAjaxData(pageService,page,COSTCONTENT);
    }


    @GetMapping("/project/{page}")
    public ModelAndView businessProject(@PathVariable Integer page){
        return completeData(pageService,htmlEntity,textEntities,ZITEMLISTTEMPLATE,PROJECTTEXTENTITY,PROJECTCONTENT,page,true);
    }
    @GetMapping("/project/ajax/{page}")
    @ResponseBody
    public ImgTextResponse newsMessageAjaxProject(@PathVariable Integer page){
        return completeAjaxData(pageService,page,PROJECTCONTENT);
    }

    @GetMapping("/message/{pageId}/{textId}")
    public ModelAndView bussinessPassage(@PathVariable("pageId") int pageId ,@PathVariable("textId") Long textId){
        BaseText byId = textService.getBaseTextById(textId);
        return completeStringData(htmlEntity,textEntities,TEXTTEMPLATE,pageId,byId);
    }





}
