/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.exigen.ipb.confluence.client.model;

/**
 * ServerInfo implementation of remote confluence data object
 * @see <a href="http://developer.atlassian.com/confdev/confluence-rest-api/confluence-xml-rpc-and-soap-apis/remote-confluence-data-objects">Remote confluence data objects</a>
 *
 * @autor esagan on 1/18/2016.
 * @version 1.0
 */
public class ServerInfo {
    private int majorVersion;//the major version number of the Confluence instance
    private int minorVersion;//the minor version number of the Confluence instance
    private int pathLevel;//the patch-level of the Confluence instance
    private String buildId;//the build ID of the Confluence instance (usually a number)
    private Boolean developmentBuild;//Whether the build is a developer-only release or not
    private String baseUrl;//The base URL for the confluence instance

    public int getMajorVersion() {
        return majorVersion;
    }

    public void setMajorVersion(int majorVersion) {
        this.majorVersion = majorVersion;
    }

    public int getMinorVersion() {
        return minorVersion;
    }

    public void setMinorVersion(int minorVersion) {
        this.minorVersion = minorVersion;
    }

    public int getPathLevel() {
        return pathLevel;
    }

    public void setPathLevel(int pathLevel) {
        this.pathLevel = pathLevel;
    }

    public String getBuildId() {
        return buildId;
    }

    public void setBuildId(String buildId) {
        this.buildId = buildId;
    }

    public Boolean getDevelopmentBuild() {
        return developmentBuild;
    }

    public void setDevelopmentBuild(Boolean developmentBuild) {
        this.developmentBuild = developmentBuild;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
