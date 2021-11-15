/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.exigen.ipb.confluence.client.model;

/**
 * Page summary implementation of remote confluence data object
 * @see <a href="http://developer.atlassian.com/confdev/confluence-rest-api/confluence-xml-rpc-and-soap-apis/remote-confluence-data-objects">Remote confluence data objects</a>
 *
 * @author esagan on 1/14/2016.
 * @version
 */
public class PageSummary {
    private long id;//the id of the page
    private String space;//the key of the space that this page belongs to
    private long parentId;//the id of the parent page
    private String title;//the title of the page
    private String url;//the url to view this page online
    private int permissions;//the number of permissions on this page (deprecated: may be removed in a future version)

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

    public int getPermissions() {
        return permissions;
    }

    public void setPermissions(int permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "PageSummary{" +
                "id=" + id +
                ", space='" + space + '\'' +
                ", parentId=" + parentId +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", permissions=" + permissions +
                '}';
    }
}
