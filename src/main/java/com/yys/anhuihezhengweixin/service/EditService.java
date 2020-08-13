package com.yys.anhuihezhengweixin.service;

import com.yys.anhuihezhengweixin.dao.HTMLDao;
import com.yys.anhuihezhengweixin.dao.OtherImgDao;
import com.yys.anhuihezhengweixin.dao.SideBarDao;
import com.yys.anhuihezhengweixin.dao.TextDao;
import com.yys.anhuihezhengweixin.entity.base.HTMLEntity;
import com.yys.anhuihezhengweixin.entity.base.OtherImg;
import com.yys.anhuihezhengweixin.entity.base.Sidebar;
import com.yys.anhuihezhengweixin.entity.base.TextEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Service
public class EditService {


    private final OtherImgDao otherImgDao;

    @Autowired
    public EditService(OtherImgDao otherImgDao) {
        this.otherImgDao = otherImgDao;
    }


    public OtherImg saveOtherImg(OtherImg otherImg){
        return otherImgDao.save(otherImg);
    }



    public OtherImg findOtherImgByPicId(Integer picId){
        List<OtherImg> allByPicId = otherImgDao.findAllByPicId(picId);
        if(allByPicId == null || allByPicId.size()<1 ){
            return null;
        } else {
            return allByPicId.get(0);
        }
    }

    public List<OtherImg> findAllOtherImg(){
        return otherImgDao.findAll();
    }
}
