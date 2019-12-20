package com.telink.bluetooth.light.ui;

import com.telink.bluetooth.light.TelinkLightService;

/**
 * @Author: zhujiang
 * @Date: 2019/12/20 16:09
 */
public class CmdManager {

    public static void allControl(boolean isOnoff) {
        byte opcode = (byte) 0xD0;
        int address = 0xFFFF;
        byte[] params = new byte[]{ (byte) (isOnoff ? 0x01 : 0x00), 0x00, 0x00};
        TelinkLightService.Instance().sendCommandNoResponse(opcode, address,
                params);
    }

}
