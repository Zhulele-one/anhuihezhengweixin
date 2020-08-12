package com.yys.anhuihezhengweixin.controller;

import com.yys.anhuihezhengweixin.entity.base.OtherImg;
import com.yys.anhuihezhengweixin.entity.base.Pay;
import com.yys.anhuihezhengweixin.entity.base.Record;
import com.yys.anhuihezhengweixin.response.SimpleResponse;
import com.yys.anhuihezhengweixin.service.EditService;
import com.yys.anhuihezhengweixin.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static com.yys.anhuihezhengweixin.util.HtmlTag.*;

@Controller
public class ZIndexController {

    private List<OtherImg> bannerList = new ArrayList<>();
    private List<OtherImg> businessImgList = new ArrayList<>();
    private OtherImg aboutImg = new OtherImg();


    private final EditService editService;

    private final FormService formService;

    @Autowired
    public ZIndexController(EditService editService, FormService formService) {
        this.editService = editService;
        this.formService = formService;
    }

    @GetMapping("/index")
    public String index(){
        return MODELTEMPLATE;
    }

    @GetMapping("/zIndex")
    public String zIndex(){
        return ZINDEXTEMPLATE;
    }

    @GetMapping("/indexSetting")
    public ModelAndView indexSetting(){
        ModelAndView mv = new ModelAndView(ZINDEXSETTINGTEMPLATE);

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


        List<Pay> pays = formService.getPays();
        mv.addObject("pays",pays);
        List<Record> records = formService.getRecords();
        mv.addObject("records",records);


        return mv;
    }

    @GetMapping("/addPay/{payZoom}")
    @ResponseBody
    public SimpleResponse addPay(@PathVariable String payZoom){
        Long payCount = formService.getPayCount();
        Pay pay = new Pay();
        pay.setPayId((int) (payCount + 1));
        pay.setPayZoom(payZoom);
        Pay pay1 = formService.savePay(pay);
        if(pay1 != null){
            return new SimpleResponse("保存成功",SimpleResponse.SUCCESS);
        } else {
            return new SimpleResponse("保存失败",SimpleResponse.ERROR);
        }
    }

    @GetMapping("/delPay/{id}")
    @ResponseBody
    public SimpleResponse delPay(@PathVariable Long id){

        Pay payById = formService.findPayById(id);
        if(payById == null){
            return new SimpleResponse("你要删除的信息不存在",SimpleResponse.ERROR);
        }

        formService.deletePay(payById);

        return new SimpleResponse("删除成功",SimpleResponse.SUCCESS);

    }

    @GetMapping("/editPay/{payId}/{payZoom}")
    @ResponseBody
    public SimpleResponse editPay(@PathVariable("payId")Long payId,@PathVariable("payZoom") String payZoom){

        Pay payById = formService.findPayById(payId);
        if(payById == null){
            return new SimpleResponse("修改失败,此信息不存在",SimpleResponse.ERROR);
        }
        payById.setPayZoom(payZoom);
        Pay pay1 = formService.savePay(payById);

        if(pay1 != null){
            return new SimpleResponse("修改成功",SimpleResponse.SUCCESS);
        } else {
            return new SimpleResponse("修改失败",SimpleResponse.SUCCESS);
        }
    }



    @GetMapping("/addRecord/{recordName}")
    @ResponseBody
    public SimpleResponse addRecord(@PathVariable String recordName){
        Long payCount = formService.getRecordCount();
        Record record = new Record();
        record.setRecordId((int) (payCount + 1));
        record.setRecordName(recordName);
        Record record1 = formService.saveRecord(record);
        if(record1 != null){
            return new SimpleResponse("保存成功",SimpleResponse.SUCCESS);
        } else {
            return new SimpleResponse("保存失败",SimpleResponse.ERROR);
        }
    }


    @GetMapping("/delRecord/{id}")
    @ResponseBody
    public SimpleResponse delRecord(@PathVariable Long id){

        Record recordById = formService.findRecordById(id);

        if(recordById == null){
            return new SimpleResponse("你要删除的信息不存在",SimpleResponse.ERROR);
        }

        formService.deleteRecord(recordById);

        return new SimpleResponse("删除成功",SimpleResponse.SUCCESS);

    }

    @GetMapping("/editRecord/{recordId}/{recordName}")
    @ResponseBody
    public SimpleResponse editRecord(@PathVariable("recordId")Long recordId,@PathVariable("recordName") String recordName){

        Record recordById = formService.findRecordById(recordId);
        if(recordById == null){
            return new SimpleResponse("修改失败,此信息不存在",SimpleResponse.ERROR);
        }
        recordById.setRecordName(recordName);
        Record record = formService.saveRecord(recordById);

        if(record != null){
            return new SimpleResponse("修改成功",SimpleResponse.SUCCESS);
        } else {
            return new SimpleResponse("修改失败",SimpleResponse.SUCCESS);
        }
    }


}
