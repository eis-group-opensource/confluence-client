/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.exigen.ipb.confluence.client.command;

import com.exigen.ipb.confluence.client.util.Logger;

import java.util.List;

/**
 * Command context interface for future implementation for particular
 * {@link com.exigen.ipb.confluence.client.command.Command Command}
 * and should be used as parameter in method
 * {@link com.exigen.ipb.confluence.client.command.Command#execute(Object)}
 *
 * @autor esagan on 2/4/2016.
 * @version 1.0
 */
public interface CommandCtx {

    List<String> getListOfPages();

    Logger getLogger();
}
