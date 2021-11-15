/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.exigen.ipb.confluence.client;

import com.exigen.ipb.confluence.client.command.Command;
import com.exigen.ipb.confluence.client.command.CommandCtx;
import com.exigen.ipb.confluence.client.command.CommandFactory;
import com.exigen.ipb.confluence.client.command.impl.DeleteChildPagesCommandCtx;
import com.exigen.ipb.confluence.client.config.Configuration;
import com.exigen.ipb.confluence.client.util.JavaLoggerWrapper;
import com.google.common.collect.Lists;
import org.junit.Ignore;
import org.junit.Test;

import java.util.logging.Logger;

/**
 * @autor esagan
 * @since 1.0
 */
@Ignore(value = "Manual run only. Test for debug purposes")
public class DeleteChildPagesCommandTest extends AbstractCommandTest {

    @Test
    public void shouldDeletePagesWithListOfPagesArgument() throws Exception {
        //given
        Configuration configuration = getConfiguration();
        Command<Void, CommandCtx> command = CommandFactory.getDeleteChildPagesCommand(configuration);

        DeleteChildPagesCommandCtx commandCtx = new DeleteChildPagesCommandCtx(new JavaLoggerWrapper(Logger.getAnonymousLogger()), Lists.<String>newArrayList("Test Child1 Page","Test Child3 Page"));
        command.execute(commandCtx);


    }

    @Test
    public void shouldDeleteChildPagesWithoutCmdArgument() throws Exception {
        //given
        Configuration configuration = getConfiguration();
        Command<Void, CommandCtx> command = CommandFactory.getDeleteChildPagesCommand(configuration);

        command.execute();


    }
}
