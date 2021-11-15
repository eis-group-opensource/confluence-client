/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.exigen.ipb.confluence.client.config.impl;

/**
 * Confluence configuration provider
 *
 * @autor esagan on 2/4/2016.
 * @version 1.0
 */
public class ConfluenceConfiguration {

    private String userName;

    private String password;

    private String serverURI;

    private String spaceKey;

    private String rootPageTitle;

    private String pagePrefix = "";

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getServerURI() {
        return serverURI;
    }

    public void setServerURI(String serverURI) {
        this.serverURI = serverURI;
    }

    public String getSpaceKey() {
        return spaceKey;
    }

    public void setSpaceKey(String spaceKey) {
        this.spaceKey = spaceKey;
    }

    public String getRootPageTitle() {
        return rootPageTitle;
    }

    public void setRootPageTitle(String rootPageTitle) {
        this.rootPageTitle = rootPageTitle;
    }

    public String getPagePrefix() {
        return pagePrefix;
    }

    public void setPagePrefix(String pagePrefix) {
        this.pagePrefix = pagePrefix;
    }

    @Override
    public String toString() {
        return "ConfluenceConfiguration{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", serverURI='" + serverURI + '\'' +
                ", spaceKey='" + spaceKey + '\'' +
                ", rootPageTitle='" + rootPageTitle + '\'' +
                ", pagePrefix='" + pagePrefix + '\'' +
                '}';
    }
}
