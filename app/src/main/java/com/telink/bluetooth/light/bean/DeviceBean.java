package com.telink.bluetooth.light.bean;

import lombok.Data;

/**
 * @Author: zhujiang
 * @Date: 2019/12/18 10:46
 */
@Data
public class DeviceBean {

    private String id;
    private String name;
    private String net;
    private String psd;
    private String mac;

    private boolean onLine;


}
