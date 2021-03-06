/********************************************************************************************************
 * @file     QRDecoder.java 
 *
 * @brief    for TLSR chips
 *
 * @author	 telink
 * @date     Sep. 30, 2010
 *
 * @par      Copyright (c) 2010, Telink Semiconductor (Shanghai) Co., Ltd.
 *           All rights reserved.
 *           
 *			 The information contained herein is confidential and proprietary property of Telink 
 * 		     Semiconductor (Shanghai) Co., Ltd. and is available under the terms 
 *			 of Commercial License Agreement between Telink Semiconductor (Shanghai) 
 *			 Co., Ltd. and the licensee in separate contract or the terms described here-in. 
 *           This heading MUST NOT be removed from this file.
 *
 * 			 Licensees are granted free, non-transferable use of the information in this 
 *			 file under Mutual Non-Disclosure Agreement. NO WARRENTY of ANY KIND is provided. 
 *           
 *******************************************************************************************************/
package com.telink.bluetooth.light.qrcode;

import android.graphics.Bitmap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;

public final class QRDecoder {

    private Builder mBuilder;
    private QRCodeReader mReader;

    private QRDecoder(Builder builder) {
        this.mBuilder = builder;
        this.mReader = new QRCodeReader();
    }

    public Result decode(final Bitmap image) throws FormatException, ChecksumException, NotFoundException {
        this.mReader.reset();
        int width = image.getWidth();
        int height = image.getHeight();
        int[] pixels = new int[width * height];
        image.getPixels(pixels, 0, width, 0, 0, width, height);
        LuminanceSource source = new RGBLuminanceSource(width, height, pixels);
        Binarizer binarizer = new HybridBinarizer(source);
        BinaryBitmap bitmap = new BinaryBitmap(binarizer);
        return this.mReader.decode(bitmap, this.mBuilder.hints);
    }

    public Result decode(LuminanceSource source) throws FormatException, ChecksumException, NotFoundException {
        Binarizer binarizer = new HybridBinarizer(source);
        BinaryBitmap bitmap = new BinaryBitmap(binarizer);
        return this.mReader.decode(bitmap, this.mBuilder.hints);
    }

    public static class Builder {

        private Map<DecodeHintType, Object> hints;
        private String charset = "UTF-8";

        public void setCharset(String charset) {
            if (charset == null || charset.trim().isEmpty())
                charset = "UTF-8";
            this.charset = charset;
        }

        private Map<DecodeHintType, Object> buildHints() {
            Map<DecodeHintType, Object> hints = new EnumMap<>(DecodeHintType.class);
            hints.put(DecodeHintType.CHARACTER_SET, this.charset);
            Collection<BarcodeFormat> formats = new ArrayList<>();
            formats.add(BarcodeFormat.QR_CODE);
            hints.put(DecodeHintType.POSSIBLE_FORMATS, formats);
            return hints;
        }

        public QRDecoder build() {
            this.hints = this.buildHints();
            return new QRDecoder(this);
        }
    }
}
