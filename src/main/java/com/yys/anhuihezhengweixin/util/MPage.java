package com.yys.anhuihezhengweixin.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MPage {

    private Long totalElements;
    private Integer size;
    private Integer totalPages;
    private Integer number;
}
