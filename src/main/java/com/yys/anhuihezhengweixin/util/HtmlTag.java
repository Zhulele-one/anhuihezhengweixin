package com.yys.anhuihezhengweixin.util;

import lombok.ToString;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * @author zq
 * 此类用于记录 所有页面标识
 */

@ToString
public final class HtmlTag {
    //所有页面标识

    public static final Integer SIZE = 15;

    public static final String HTML = "HTML";

    //模板标识(对应模板名称)

    public static final String TEXTTEMPLATE = "text";
    public static final String RECRUITTEMPLATE = "recruit";
    public static final String FORMTEMPLATE = "recruitForm";
    public static final String CALLBACKFORMTEMPLATE = "callBackFrom";
    public static final String IMGLISTTEMPLATE = "imgList";
    public static final String ITEMLISTTEMPLATE = "itemList";
    public static final String PREVIEWTEMPLATE = "preview";
    public static final String CALLMETEMPLATE = "callme";
    public static final String FORMLISTTEMPLATE = "formList";

    public static final String ZITEMLISTTEMPLATE = "zItemList";
    public static final String ZFORMLISTTEMPLATE = "zFormList";
    public static final String ZBACKFORMLISTTEMPLATE = "zBackFormList";
    public static final String ZPREVIEWTEMPLATE = "zPreview";
    public static final String ZRECRUITTEMPLATE = "zRecruit";
    public static final String ZINDEXTEMPLATE = "zIndex";
    public static final String ZINDEXSETTINGTEMPLATE = "zIndexSetting";
    public static final String MODELTEMPLATE = "model";


////////////////////index
    public static final Integer INDEXID = 1;

////////////////////endIndex


//////////////////about

    public static final Integer ABOUTID = 2;

    ///entity对应text表 按照sideid排序

    public static final Integer BIREFIRETEXTENTITY = 1;
    public static final Integer HONORTEXTENTITY = 2;
    public static final Integer ACHIEVEMENTETEXTENTITY = 3;
    public static final Integer SONCOMPANYTEXTENTITY = 4;



    ///content内容标识

    public static final Integer BIREFINGCONTENT = 1;
    public static final Integer HONORECONTENT = 2;
    public static final Integer ACHIEVEMENTCONTENT = 3;

    public static final Integer ANHUIJUCHENGCOMPANYCONENT = 5;
    public static final Integer ANHUIHEZHENGZAOJIACOMPANYCONTENT = 6;


/////////////////End about


/////////////////news


    public static final Integer NEWSID = 3;

    public static final Integer RULETEXTENTITY = 3;
    public static final Integer COMPANYNEWSTEXTENTITY = 1;
    public static final Integer HANGYENEWSTEXTENTITY = 2;
    public static final Integer DIFANGRULETEXTENTITY = 4;
    public static final Integer COUNTRYRULETEXTENTITY = 5;
    public static final Integer LEARNINGTEXTENTITY = 6;

    public static final Integer RULELISTCONTENT = 9;
    public static final Integer COMPANYNEWSCONETNT = 7;
    public static final Integer HANGYENEWSCONETNT = 8;
    public static final Integer DIFANGRULRULECONTENT = 10;
    public static final Integer COUNTRYRULECONTENT = 11;
    public static final Integer LEARNINGCONTENT = 12;
/////////////////End news


//////////////////business

    public static final Integer BUSINESSID = 4;
    ///entity对应text表 按照sideid排序
    public static final Integer PROXYTEXTENTITY = 1;
    public static final Integer SUPERVISIONTEXTENTITY = 2;
    public static final Integer COSTTEXTENTITY = 3;
    public static final Integer PROJECTTEXTENTITY = 4;

    ///content内容标识
    public static final Integer PROXYCONTENT = 13;
    public static final Integer SUPERVISIONCONTENT = 14;
    public static final Integer COSTCONTENT = 15;
    public static final Integer PROJECTCONTENT = 16;
/////////////////endbusiness

/////////////////culture

    public static final Integer CULTUREID = 5;
    ///entity对应text表 按照sideid权重排序

    public static final Integer CULTUREEXTENTITY = 1;


    ///content内容标识
    public static final Integer CULTURECONTENT = 17;

////////////////end culture


////////////////recruit

    public static final Integer RECRUITID = 6;

    public static final Integer RECRUITTEXTENTITY = 1;

    public static final Integer RECRUITCONENT = 18;
///////////////recruit end

////////////////aboutme

    public static final Integer ABOUTMEID = 7;

    ///entity对应text表 按照sideid排序
    public static final Integer ABOUTMETEXTENTITY = 1;

    ///content内容标识
    public static final Integer ABOUTMECONENT = 24;
///////////////en aboutme


////////////////preview
    public static final Integer PREVIEWID = 8;

    public static final Integer EDITTEXTENTITY = 1;
    public static final Integer PREVIEWTEXTENTITY = 2;

///////////////endpreview

}
