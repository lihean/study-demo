package com.example.study.demo.hystrix;

public class Response {

    private String code;

    private Object data;

    public Response(String code){
        this.code = code;
    }

    public Response(String code,String data){
        this.code = code;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Response{" +
                "code='" + code + '\'' +
                ", data=" + data +
                '}';
    }
}
