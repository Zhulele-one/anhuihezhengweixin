package com.yys.anhuihezhengweixin.controller;

import com.yys.anhuihezhengweixin.entity.base.HTMLEntity;
import com.yys.anhuihezhengweixin.entity.base.OtherImg;
import com.yys.anhuihezhengweixin.entity.base.Sidebar;
import com.yys.anhuihezhengweixin.entity.base.TextEntity;
import com.yys.anhuihezhengweixin.entity.content.ImgText;
import com.yys.anhuihezhengweixin.service.EditService;
import com.yys.anhuihezhengweixin.service.PageService;
import com.yys.anhuihezhengweixin.service.TextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.yys.anhuihezhengweixin.util.HtmlTag.*;

@Controller
public class IndexController {

    private final PageService pageService;

    private final EditService editService;

    private List<OtherImg> bannerList = new ArrayList<>();
    private List<OtherImg> businessImgList = new ArrayList<>();
    private OtherImg aboutImg = new OtherImg();


    @Autowired
    public IndexController(PageService pageService,EditService editService) {
        this.pageService = pageService;
        this.editService = editService;
    }

    //返回首页数据方法

    @GetMapping("/__index__")
    public ModelAndView businessProxy(){

        ModelAndView mv = new ModelAndView("index");

        List<HTMLEntity> htmlEntites = pageService.getAllHTMLEntity();


        mv.addObject("HTMLEntityList",htmlEntites);


        ////简介显示
        Page<ImgText> content3 = pageService.getContent(BIREFINGCONTENT, 1);
        List<ImgText> birefingList = content3.getContent();



        ///新闻显示
        List<ImgText> content = pageService.getContent(COMPANYNEWSCONETNT,1).getContent();
        List<ImgText> content1 = pageService.getContent(HANGYENEWSCONETNT,1).getContent();
        List<ImgText> newsList = new ArrayList<>();
        int difangLen = content.size();
        if(content.size() > 2){
            difangLen = 2;
        }
        for (int i = 0; i < difangLen; i++) {
            newsList.add(content.get(i));
        }
        int countryLen = content1.size();
        if(content1.size() > 2){
            countryLen = 2;
        }
        for (int i = 0; i < countryLen; i++) {
            newsList.add(content1.get(i));
        }

        System.out.println(newsList);


        ////文化党建显示
        List<ImgText> content2 = pageService.getContent(CULTURECONTENT,1).getContent();
        List<ImgText> content5 = pageService.getContent(CULTURECONTENT,2).getContent();

        List<ImgText> cultureAllList = new ArrayList<>();
        cultureAllList.addAll(content2);
        cultureAllList.addAll(content5);

        int cultureLen = cultureAllList.size();
        if(cultureLen > 16){
            cultureLen = 16;
        }

        ArrayList<ImgText> cultureList = new ArrayList<>();
        for (int i = 0; i < cultureLen; i++) {
            cultureList.add(cultureAllList.get(i));
        }


        ///荣誉资质显示
        List<ImgText> honorList = pageService.getContent(HONORECONTENT,1).getContent();


        ///招标代理
        List<Sidebar> businessList = pageService.findAllSidebarByGroupId(3);

        mv.addObject("businessList",businessList);

        mv.addObject("honorList",honorList);
        mv.addObject("newsList",newsList);
        mv.addObject("cultureList",cultureList);
        mv.addObject("birefingList",birefingList);

        if(bannerList.size() < 3 || businessImgList.size() < 4 || aboutImg.getPicId() == null){
            List<OtherImg> allOtherImg = editService.findAllOtherImg();
            for (OtherImg o:allOtherImg){
                if(bannerList.size() >= 3){
                    break;
                }

                if(o.getPicId() <= 3){
                    bannerList.add(o);
                }

            }

            for (OtherImg o : allOtherImg){
                if(o.getPicId() == 4){
                    aboutImg = o;
                    break;
                }
            }

            for (OtherImg o : allOtherImg){
                if(businessImgList.size() >= 4){
                    break;
                }
                if(o.getPicId() > 4 && o.getPicId() <= 8){
                    businessImgList.add(o);
                }

            }
            if(businessImgList.size() <4){
                businessImgList = null;
            }
        }

        mv.addObject("bannerList",bannerList);
        mv.addObject("aboutImg",aboutImg);
        mv.addObject("businessImgList",businessImgList);

        mv.addObject(HTML,htmlEntites.get(0));
        return mv;
    }

}
