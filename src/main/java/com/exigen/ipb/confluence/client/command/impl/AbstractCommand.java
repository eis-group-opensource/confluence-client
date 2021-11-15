/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.exigen.ipb.confluence.client.command.impl;

import com.exigen.ipb.confluence.client.command.Command;
import com.exigen.ipb.confluence.client.config.Configuration;

/**
 * Base class for any command {@link com.exigen.ipb.confluence.client.command.Command} implementation
 * It keeps common command logic and state.
 *
 * @autor esagan on 2/4/2016.
 * @version 1.0
 */
public abstract class AbstractCommand<O,I> implements Command<O,I> {

    protected Configuration configuration;

    /**
     * Any command cannot exist without configuration information
     *
     * @param configuration - {@link com.exigen.ipb.confluence.client.config.Configuration}
     */
    public AbstractCommand(Configuration configuration) {
        this.configuration = configuration;
    }
}
