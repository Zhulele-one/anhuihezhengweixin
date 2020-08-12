package com.yys.anhuihezhengweixin.config;


import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/login","/login/login.html","/hezheng/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            //指定登录页的路径
            .loginPage("/login")
            //指定自定义form表单请求的路径
            .loginProcessingUrl("/authentication/form")
            .failureUrl("/fail")
            .defaultSuccessUrl("/success")
            .permitAll();

        http.headers().frameOptions().disable();
        http.csrf().disable();
    }
}
