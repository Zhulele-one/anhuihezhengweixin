package com.yys.anhuihezhengweixin.controller;

import com.yys.anhuihezhengweixin.entity.base.*;
import com.yys.anhuihezhengweixin.response.SimpleResponse;
import com.yys.anhuihezhengweixin.service.FormService;
import com.yys.anhuihezhengweixin.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.yys.anhuihezhengweixin.util.HTMLUtils.completeData;
import static com.yys.anhuihezhengweixin.util.HtmlTag.*;

/**
 * @author zq
 */
@Controller
@RequestMapping("/__aboutme__/")
public class AboutMeController {


    private HTMLEntity htmlEntity;
    private List<TextEntity> textEntities;
    private List<Sidebar> sidebars;

    private final PageService pageService;
    private final FormService formService;

    @Autowired
    public AboutMeController(PageService pageService, FormService formService) {
        this.pageService = pageService;
        this.formService = formService;
    }


    @ModelAttribute
    public void init(){
        if(textEntities == null){
            if(sidebars == null){
                sidebars = pageService.initSidebar(ABOUTMEID);
            }
            textEntities = pageService.initPageText(ABOUTMEID,sidebars);
        }
        if(htmlEntity == null){
            htmlEntity = pageService.initPage(ABOUTMEID);
        }

    }





    @GetMapping("/callback")
    public ModelAndView aboutMeCallBack(){
        ModelAndView mv = completeData(pageService,htmlEntity,textEntities,
                CALLBACKFORMTEMPLATE,ABOUTMETEXTENTITY,null,false);

        List<BackType> backTypes = formService.getBackTypes();
        mv.addObject("backTypes",backTypes);

        return mv;
    }

    @GetMapping("/callme")
    public ModelAndView aboutMeCallMe(){
        return completeData(pageService,htmlEntity,textEntities,
                CALLMETEMPLATE,ABOUTMETEXTENTITY,null,false);
    }

    @PostMapping("/callback/message")
    @ResponseBody
    public SimpleResponse aboutMeCallBackMessage(BackFormEntity backFormEntity){
        System.out.println(backFormEntity);
        if(backFormEntity == null){
            return new SimpleResponse("提交失败",SimpleResponse.ERROR);
        }
        if(formService.saveFormBack(backFormEntity) == null){
            return new SimpleResponse("提交失败",SimpleResponse.ERROR);
        } else {
            return new SimpleResponse("提交成功",SimpleResponse.SUCCESS);
        }
    }

}
