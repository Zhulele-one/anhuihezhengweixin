package com.yys.anhuihezhengweixin.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleResponse {
    private String msg;
    private String[] msgs;
    private Boolean code;
    public static final Boolean SUCCESS = true;
    public static final Boolean ERROR = false;

    public SimpleResponse(String[] msgs, Boolean code) {
        this.msgs = msgs;
        this.code = code;
    }

    public SimpleResponse(String msg, Boolean code) {
        this.msg = msg;
        this.code = code;
    }
}
