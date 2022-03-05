package com.taohuasquare.chatline.entity;

/**
 * @author happy
 * @since 2022-03-05
 */
public class RpcRequest {
    private String id;
    private Object data;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RpcRequest{" +
                "id='" + id + '\'' +
                ", data=" + data +
                '}';
    }
}
