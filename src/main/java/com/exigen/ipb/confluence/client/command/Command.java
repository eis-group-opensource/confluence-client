/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.exigen.ipb.confluence.client.command;

/**
 * Core Confluence client command interface.
 * Commands are more business logic layer over low-level confluence client implementation
 *
 * @autor esagan on 2/4/2016.
 * @version 1.0
 */
public interface Command<O,I> {

    /**
     * Execute command and return result
     * can throw EisConfluenceException {@link com.exigen.ipb.confluence.client.exception.EisConfluenceException}
     * @return O - output type
     * @throws com.exigen.ipb.confluence.client.exception.EisConfluenceException
     */
    O execute();

    /**
     * Execute command and return result,
     * can throw EisConfluenceException {@link com.exigen.ipb.confluence.client.exception.EisConfluenceException}
     * @param input - input type
     *                in most cases it could be 'command context'
     *               {@link com.exigen.ipb.confluence.client.command.CommandCtx}
     * @return  - output type
     * @throws com.exigen.ipb.confluence.client.exception.EisConfluenceException
     */
    O execute(I input);
}
