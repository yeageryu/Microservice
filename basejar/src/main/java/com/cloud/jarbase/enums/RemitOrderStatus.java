package com.cloud.jarbase.enums;

public enum RemitOrderStatus {
    WAIT_RECHARGE(0, "created", "待充值"),
    WAIT_LOCKRATE(1, "paid", "待锁汇"),   //用户下单后，去线下付款或线上转账方式进行充值，如果能发起锁汇交易，则会跳过该状态(因为不能把订单改为已支付，因为怕余额不够，够了又直接锁汇了)
    WAIT_EXCHANGE(2, "locked rate", "待换汇"),
    WAIT_TRANS(3, "exchanged", "待下发"),
    WAIT_TRANS_RESULT(4, "apply", "待下发结果"),
    SUCCESS(5, "success", "下发成功"),
    REFUND(6, "refund", "已退款"),
    FAILED(99, "", "失败");

    private int value;
    private String nameEn;
    private String nameCn;

    RemitOrderStatus(int value, String nameEn, String nameCn) {
        this.value = value;
        this.nameEn = nameEn;
        this.nameCn = nameCn;
    }

    RemitOrderStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public String getNameEn() {
        return this.nameEn;
    }

    public String getNameCn() {
        return this.nameCn;
    }

}
