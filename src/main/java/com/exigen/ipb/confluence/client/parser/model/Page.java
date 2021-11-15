/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.exigen.ipb.confluence.client.parser.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Page tree structure to keep parsed input information for future population on confluence
 *
 * @author esagan on 1/16/2016.
 * @version 1.0
 */
public class Page {

    private Page parent = null;
    private List<Page> childs = new ArrayList<>();
    private String content;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Page getParent() {
        return parent;
    }

    public void setParent(Page parent) {
        this.parent = parent;
    }

    public List<Page> getChilds() {
        return childs;
    }


    public void addChild(Page child) {
        if (child == null) {
            return;
        }
        child.setParent(this);
        this.childs.add(child);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Page{" +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
//                ", childs=" + childs +
                "parent=" + parent +
                '}';
    }
}
