package com.lss.bean.form;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TimestampFilterItem {
    private String name;
    private Long start;
    private Long end;
}
