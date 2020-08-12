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

import static com.yys.anhuihezhengweixin.util.HTMLUtils.completeAjaxData;
import static com.yys.anhuihezhengweixin.util.HTMLUtils.completeData;
import static com.yys.anhuihezhengweixin.util.HTMLUtils.completeStringData;
import static com.yys.anhuihezhengweixin.util.HtmlTag.*;
import static com.yys.anhuihezhengweixin.util.UrlUtils.completeUrl;

@Controller
@RequestMapping("/__business__/")
public class BusinessController {

    private HTMLEntity htmlEntity;
    private List<TextEntity> textEntities;
    private List<Sidebar> sidebars;

    private final PageService pageService;

    private final TextService textService;

    @Autowired
    public BusinessController(PageService pageService, TextService textService) {
        this.pageService = pageService;
        this.textService = textService;
    }


    @ModelAttribute
    public void init(){
        if(textEntities == null){
            if(sidebars == null){
                sidebars = pageService.initSidebar(BUSINESSID);
            }
            textEntities = pageService.initPageText(BUSINESSID,sidebars);
        }
        if(htmlEntity == null){
            htmlEntity = pageService.initPage(BUSINESSID);
        }

    }

    @GetMapping("/proxy/{page}")
    public ModelAndView businessProxy(@PathVariable Integer page){

        return completeData(pageService,htmlEntity,textEntities,ITEMLISTTEMPLATE,PROXYTEXTENTITY,PROXYCONTENT,page,true);
    }

    @GetMapping("/proxy/ajax/{page}")
    @ResponseBody
    public ImgTextResponse newsMessageAjaxProxy(@PathVariable Integer page){
        return completeAjaxData(pageService,page,PROXYCONTENT);
    }

    @GetMapping("/supervision/{page}")
    public ModelAndView businessSupervision(@PathVariable Integer page){
        return completeData(pageService,htmlEntity,textEntities,ITEMLISTTEMPLATE,SUPERVISIONTEXTENTITY,SUPERVISIONCONTENT,page,true);
    }

    @GetMapping("/supervision/ajax/{page}")
    @ResponseBody
    public ImgTextResponse newsMessageAjaxSupervision(@PathVariable Integer page){
        return completeAjaxData(pageService,page,SUPERVISIONCONTENT);
    }

    @GetMapping("/cost/{page}")
    public ModelAndView businessCost(@PathVariable Integer page){
        return completeData(pageService,htmlEntity,textEntities,ITEMLISTTEMPLATE,COSTTEXTENTITY,COSTCONTENT,page,true);
    }
    @GetMapping("/cost/ajax/{page}")
    @ResponseBody
    public ImgTextResponse newsMessageAjaxCost(@PathVariable Integer page){
        return completeAjaxData(pageService,page,COSTCONTENT);
    }


    @GetMapping("/project/{page}")
    public ModelAndView businessProject(@PathVariable Integer page){
        return completeData(pageService,htmlEntity,textEntities,ITEMLISTTEMPLATE,PROJECTTEXTENTITY,PROJECTCONTENT,page,true);
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
