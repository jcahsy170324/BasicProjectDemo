package com.lg.bsp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName OperationRecord
 * @Description 操作记录类
 * @Author jincheng
 * @Date 2022/1/10 13:49
 * @Version 1.0
 **/
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationRecord implements Serializable {
    private Integer id;
    private String operationUserId;
    private Date createTime;
    private Integer operationType;
    private String operationObjectId;
    private String operationContent;
}
