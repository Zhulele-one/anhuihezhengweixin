package com.yys.anhuihezhengweixin.response;

import com.yys.anhuihezhengweixin.entity.content.ImgText;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ImgTextResponse {
    List<ImgText> imgTextList = new ArrayList<>();
    private Boolean code;
    public static final Boolean SUCCESS = true;
    public static final Boolean ERROR = false;


    public ImgTextResponse(List<ImgText> imgTextList, Boolean code) {
        this.imgTextList = imgTextList;
        this.code = code;
    }
}
