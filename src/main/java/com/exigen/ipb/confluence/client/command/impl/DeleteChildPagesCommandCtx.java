/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.exigen.ipb.confluence.client.command.impl;

import com.exigen.ipb.confluence.client.command.CommandCtx;
import com.exigen.ipb.confluence.client.exception.EisConfluenceException;
import com.exigen.ipb.confluence.client.util.Logger;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * DeleteChildPagesCommandCtx deletes child pages context class
 *
 * @version 1.0
 * @autor esagan on 4/7/2016.
 */
public class DeleteChildPagesCommandCtx implements CommandCtx {

    private List<String> listOfPages = Lists.newArrayList();
    private Logger log;

    public DeleteChildPagesCommandCtx(Logger log, List<String> listOfPages) {
        if (log == null) {
            throw new EisConfluenceException("Log attribute cannot be null");
        }
        this.log = log;

        if (listOfPages != null || !listOfPages.isEmpty()) {
            this.listOfPages = listOfPages;
        }
    }

    @Override
    public List<String> getListOfPages() {
        return listOfPages;
    }

    @Override
    public Logger getLogger() {
        return log;
    }
}
