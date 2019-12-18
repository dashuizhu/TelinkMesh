package com.telink.bluetooth.light.bean;

import java.util.List;

/**
 * mess address 从 0x8001开始
 * @Author: zhujiang
 * @Date: 2019/12/18 10:37
 */
public class GroupBean {

    private int id;
    private String name;
    private List<DeviceBean> deivceList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DeviceBean> getDeivceList() {
        return deivceList;
    }

    public void setDeivceList(List<DeviceBean> deivceList) {
        this.deivceList = deivceList;
    }
}
