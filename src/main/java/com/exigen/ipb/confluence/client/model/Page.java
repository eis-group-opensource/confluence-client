/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.exigen.ipb.confluence.client.model;

import java.util.Date;

/**
 * Page implementation of remote confluence data object
 * @see <a href="http://developer.atlassian.com/confdev/confluence-rest-api/confluence-xml-rpc-and-soap-apis/remote-confluence-data-objects">Remote confluence data objects</a>
 *
 * @author esagan on 1/14/2016.
 * @version
 */
public class Page {
    private long id; //the id of the page
    private String space; //the key of the space that this page belongs to
    private long parentId;//the id of the parent page
    private String title; //the title of the page
    private String url;//the url to view this page online
    private int version;//the version number of this page
    private String content;//the page content
    private Date created;//timestamp page was created
    private String creator;//username of the creator
    private Date modified;//timestamp page was modified
    private String modifier;//username of the page's last modifier
    private Boolean homePage;//whether or not this page is the space's homepage
    private int permissions;//the number of permissions on this page (deprecated: may be removed in a future version)
    private String contentStatus;//status of the page (eg. current or deleted)
    private Boolean current;//whether the page is current and not deleted

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSpace() {
        return space;
    }

    public void setSpace(String space) {
        this.space = space;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Boolean getHomePage() {
        return homePage;
    }

    public void setHomePage(Boolean homePage) {
        this.homePage = homePage;
    }

    public int getPermissions() {
        return permissions;
    }

    public void setPermissions(int permissions) {
        this.permissions = permissions;
    }

    public String getContentStatus() {
        return contentStatus;
    }

    public void setContentStatus(String contentStatus) {
        this.contentStatus = contentStatus;
    }

    public Boolean getCurrent() {
        return current;
    }

    public void setCurrent(Boolean current) {
        this.current = current;
    }

    @Override
    public String toString() {
        return "Page{" +
                "id=" + id +
                ", space='" + space + '\'' +
                ", parentId=" + parentId +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", version=" + version +
                ", content='" + content + '\'' +
                ", created=" + created +
                ", creator='" + creator + '\'' +
                ", modified=" + modified +
                ", modifier='" + modifier + '\'' +
                ", homePage=" + homePage +
                ", permissions=" + permissions +
                ", contentStatus='" + contentStatus + '\'' +
                ", current=" + current +
                '}';
    }

}
