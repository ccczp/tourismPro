package com.feelcode.tourism.base.constant;

public interface SystemConstant {

    /**
     * 1-已提交/审核中
     * 2-已完成/审核通过
     * 3-已完成/审核不通过
     */
    interface OrderStatus{
        Integer submit = 1;
        Integer pass = 2;
        Integer refuse = 3;
    }

    /**
     * 1-酒店
     * 2-航班
     * 3-线路
     * 4-景点
     */
    interface ProductType{
        Integer hotel = 1;
        Integer plane = 2;
        Integer line = 3;
        Integer spots = 4;
    }

}
