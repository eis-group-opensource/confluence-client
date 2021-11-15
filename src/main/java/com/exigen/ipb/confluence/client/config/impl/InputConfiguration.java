/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.exigen.ipb.confluence.client.config.impl;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Input source configuration provider
 *
 * @autor esagan on 2/4/2016.
 * @version 1.0
 */
public class InputConfiguration {

    private String documentationRootFile;

    private String documentationRootFolder;

    private String fileEncoding;

    private Boolean publicDoc = Boolean.TRUE; // don't generate xhtml information

    private List<String> inclusiveLinks = Lists.newArrayList();

    private List<String> skipDirs = Lists.newArrayList();

    public String getDocumentationRootFile() {
        return documentationRootFile;
    }

    public void setDocumentationRootFile(String documentationRootFile) {
        this.documentationRootFile = documentationRootFile;
    }

    public String getDocumentationRootFolder() {
        return documentationRootFolder;
    }

    public void setDocumentationRootFolder(String documentationRootFolder) {
        this.documentationRootFolder = documentationRootFolder;
    }

    public List<String> getInclusiveLinks() {
        return inclusiveLinks;
    }

    public void setInclusiveLinks(List<String> inclusiveLinks) {
        this.inclusiveLinks = inclusiveLinks;
    }

    public String getFileEncoding() {
        return fileEncoding;
    }

    public void setFileEncoding(String fileEncoding) {
        this.fileEncoding = fileEncoding;
    }

    public List<String> getSkipDirs() {
        return skipDirs;
    }

    public void setSkipDirs(List<String> skipDirs) {
        this.skipDirs = skipDirs;
    }

    public Boolean getPublicDoc() {
        return publicDoc;
    }

    public void setPublicDoc(Boolean publicDoc) {
        this.publicDoc = publicDoc;
    }

    @Override
    public String toString() {
        return "InputConfiguration{" +
                "documentationRootFile='" + documentationRootFile + '\'' +
                ", documentationRootFolder='" + documentationRootFolder + '\'' +
                ", fileEncoding='" + fileEncoding + '\'' +
                ", publicDoc=" + publicDoc +
                ", inclusiveLinks=" + inclusiveLinks +
                ", skipDirs=" + skipDirs +
                '}';
    }
}
