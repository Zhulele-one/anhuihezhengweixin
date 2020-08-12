package com.yys.anhuihezhengweixin.entity.setting;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
public class SettingMsg {
    private String phone;
    private String companyName;
    private String email;
    private String address;
    private String qqImgSrc;
    private String weixinImgSrc;
    private String logo;

}
