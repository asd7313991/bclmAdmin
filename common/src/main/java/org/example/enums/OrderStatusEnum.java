package org.example.enums;

public enum OrderStatusEnum {
    INIT(0,"创建成功，待审核"),
    AUDIT_SUCCESS(1,"审核成功，待支付"),
    PAY_SUCCESS(2,"支付成功"),
    AUDIT_FAIL(-1,"审核失败"),
    PAY_FAIL(-2,"支付失败"),


    ;

    private Integer code;
    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
