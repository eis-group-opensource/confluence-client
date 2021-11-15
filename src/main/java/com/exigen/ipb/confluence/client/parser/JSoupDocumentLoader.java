/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.exigen.ipb.confluence.client.parser;

import com.exigen.ipb.confluence.client.exception.EisConfluenceException;
import com.exigen.ipb.confluence.client.util.ClientConstants;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;

/**
 * Document loader to load page file
 * and provide jsoup Document object for search and parsing
 *
 * @author esagan on 1/16/2016.
 * @version 1.0
 */
public class JSoupDocumentLoader {


    private String filePath;
    private String fileEncoding = ClientConstants.FILE_ENCODING;
    private String uri = "";

    public JSoupDocumentLoader(String filePath) {
        this.filePath=filePath;
    }

    public JSoupDocumentLoader(String filePath, String encoding) {
        this.filePath=filePath;
        this.fileEncoding = encoding;
    }
    public JSoupDocumentLoader(String filePath, String encoding, String uri) {
        this.filePath=filePath;
        this.fileEncoding = encoding;
        this.uri = uri;
    }

    public Document loadDocument(String fileName){
        File input = new File(filePath+fileName);

        if (!input.exists()) {
            throw new EisConfluenceException("File: " + input.getAbsolutePath() + " not found!");
        }

        try {
            return Jsoup.parse(input, fileEncoding, uri);
        } catch (IOException e) {
            throw  new EisConfluenceException(e.getMessage(),e);
        }
    }
}
