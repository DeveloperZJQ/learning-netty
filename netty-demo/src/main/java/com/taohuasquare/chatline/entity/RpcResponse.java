package com.taohuasquare.chatline.entity;

import lombok.Data;
import lombok.ToString;

/**
 * @author happy
 * @since 2022-3-5
 */
@Data
@ToString
public class RpcResponse {
    private String id;
    private Object data;
    private Integer status;
}
