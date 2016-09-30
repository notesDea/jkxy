package com.jkxy.baiduwaimai.controllers;

/**
 * Created by notes on 2016/8/25.
 */

public class HomeItem {

    private int shopIconSrc;
    private String shopName, distance;
    private float numberOfStar;
    //卷|票|付|赔
    private boolean coupon, invoice,  pay, payment;
    //起送费|配送费|平时配送时间|距离
    private int startingFee, distributionFee, avgTime, soldNumbers;
    private String mianDetails, quanDetails, jianDetails;

    public HomeItem(int shopIconSrc, String shopName, float numberOfStar, boolean coupon,
                    boolean invoice, boolean pay, boolean payment, int startingFee,
                    int distributionFee, int avgTime, String distance, int soldNumbers,
                    String jianDetails, String mianDetails, String quanDetails) {
        this.shopIconSrc = shopIconSrc;
        this.shopName = shopName;
        this.numberOfStar = numberOfStar;
        this.coupon = coupon;
        this.invoice = invoice;
        this.pay = pay;
        this.payment = payment;
        this.startingFee = startingFee;
        this.distributionFee = distributionFee;
        this.avgTime = avgTime;
        this.distance = distance;
        this.soldNumbers = soldNumbers;
        this.mianDetails = mianDetails;
        this.quanDetails = quanDetails;
        this.jianDetails = jianDetails;
    }

    public int getShopIconSrc() {
        return shopIconSrc;
    }

    public void setShopIconSrc(int shopIconSrc) {
        this.shopIconSrc = shopIconSrc;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public boolean isCoupon() {
        return coupon;
    }

    public void setCoupon(boolean coupon) {
        this.coupon = coupon;
    }

    public boolean isInvoice() {
        return invoice;
    }

    public void setInvoice(boolean invoice) {
        this.invoice = invoice;
    }

    public boolean isPay() {
        return pay;
    }

    public void setPay(boolean pay) {
        this.pay = pay;
    }

    public boolean isPayment() {
        return payment;
    }

    public void setPayment(boolean payment) {
        this.payment = payment;
    }

    public int getStartingFee() {
        return startingFee;
    }

    public void setStartingFee(int startingFee) {
        this.startingFee = startingFee;
    }

    public int getDistributionFee() {
        return distributionFee;
    }

    public void setDistributionFee(int distributionFee) {
        this.distributionFee = distributionFee;
    }

    public int getAvgTime() {
        return avgTime;
    }

    public void setAvgTime(int avgTime) {
        this.avgTime = avgTime;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public int getSoldNumbers() {
        return soldNumbers;
    }

    public void setSoldNumbers(int soldNumbers) {
        this.soldNumbers = soldNumbers;
    }

    public float getNumberOfStar() {
        return numberOfStar;
    }

    public void setNumberOfStar(float numberOfStar) {
        this.numberOfStar = numberOfStar;
    }

    public String getMianDetails() {
        return mianDetails;
    }

    public void setMianDetails(String mianDetails) {
        this.mianDetails = mianDetails;
    }

    public String getQuanDetails() {
        return quanDetails;
    }

    public void setQuanDetails(String quanDetails) {
        this.quanDetails = quanDetails;
    }

    public String getJianDetails() {
        return jianDetails;
    }

    public void setJianDetails(String jianDetails) {
        this.jianDetails = jianDetails;
    }
}
