package com.yys.anhuihezhengweixin.controller;

import com.yys.anhuihezhengweixin.response.SimpleResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.yaml.snakeyaml.Yaml;


@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login(){
        return "login/login";
    }

    @RequestMapping("/success")
    public String success(){
        return "redirect:/index";
    }

    @RequestMapping("/fail")
    public String fail(){
        return "/login/fail";
    }

    @PostMapping("/changePassword")
    public SimpleResponse changePwd() throws Exception {

        return new SimpleResponse("修改失败",SimpleResponse.ERROR);
    }

    @GetMapping("/test")
    public String test(){
        return "1";
    }
}
