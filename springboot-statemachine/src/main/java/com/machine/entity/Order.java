package com.machine.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author wenwu.liu.o
 */
@Data
@Accessors(chain = true)
public class Order {
    private Integer orderId;
    private String address;
    private String mobile;
    private String type;
}
