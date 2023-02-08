package com.swjtu.secondhand.entity;

public class Alipay {
    // 商家订单号
    private String outTradeNo;
    // 总价格
    private double totalAmount;
    // 支付主题
    private String subject;
    // 支付宝单号
    private String alipayTraceNo;

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAlipayTraceNo() {
        return alipayTraceNo;
    }

    public void setAlipayTraceNo(String alipayTraceNo) {
        this.alipayTraceNo = alipayTraceNo;
    }
}
