/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.exigen.ipb.confluence.client.util;

import com.exigen.ipb.confluence.client.exception.EisConfluenceException;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * Utility class to provide mime type by file extension
 *
 * @autor esagan on 1/20/2016.
 * @version 1.0
 */
public final class ContentUtils {

    private ContentUtils(){}

    /**
     * Returns mime type string representation by file extension
     *
     * @param fileName - String with file extension only
     * @return String  - mime type string presentation
     */
    public static String getMimeTypeByFileExtension(String fileName) {
        return mimeTypes.get(fileName);
    }

    /**
     * Returns mime type string representation by full file name with extension
     *
     * @param fileName - String file name with extension
     * @return String  - mime type string presentation
     */
    public static String resolveMimType(String fileName) {
        String fileExtension = com.google.common.io.Files.getFileExtension(fileName);
        String mimeTypeByFileName = ContentUtils.getMimeTypeByFileExtension(fileExtension);
        if (!Strings.isNullOrEmpty(mimeTypeByFileName)){
            return mimeTypeByFileName;
        }
        throw new EisConfluenceException("Unknown mime type for file: " + fileName);
    }

    private static Map<String,String> mimeTypes = new ImmutableMap.Builder<String, String>()
                .put("jpg", "image/jpeg")
                .put("jpeg", "image/jpeg")
                .put("jpgv", "video/jpeg")
                .put("dxf", "image/vnd.dxf")
                .put("bmp", "image/bmp")
                .put("btif", "image/prs.btif")
                .put("sub", "image/vnd.dvb.subtitle")
                .put("ras", "image/x-cmu-raster")
                .put("cgm", "image/cgm")
                .put("cmx", "image/x-cmx")
                .put("uvi", "image/vnd.dece.graphic")
                .put("djvu", "image/vnd.djvu")
                .put("dwg", "image/vnd.dwg")
                .put("gif", "image/gif")
                .put("ico", "image/x-icon")
                .put("png", "image/png")
                .put("svg", "image/svg+xml")
                .put("tiff", "image/tiff")
            .build();
}
