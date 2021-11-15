/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.exigen.ipb.confluence.client;

import com.exigen.ipb.confluence.client.command.Command;
import com.exigen.ipb.confluence.client.command.CommandFactory;
import com.exigen.ipb.confluence.client.config.Configuration;
import com.exigen.ipb.confluence.client.config.impl.ClientConfiguration;
import com.exigen.ipb.confluence.client.config.impl.ConfluenceConfiguration;
import com.exigen.ipb.confluence.client.parser.UrlResult;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 *
 * https://confluence.atlassian.com/doc/confluence-storage-format-790796544.html#ConfluenceStorageFormat-Links
 *
 * NOT COVERED followed tags ac:link-body and ac:image also combinations covered and not covered tags
 *
 * @autor esagan
 * @since 1.0
 */
@Ignore(value = "Manual run only. Test for debug purposes")
public class FindBrokenLinksCommandTest extends AbstractCommandTest{

    @Test
    public void shouldFindExternalInvalidLink() throws Exception {
        //given
        Configuration configuration = getConfiguration();
        Command<Map<String, List<UrlResult>>,Void> command = CommandFactory.getFindBrokenLinksCommand(configuration);
        Map<String, List<UrlResult>> result = command.execute();

        for (Map.Entry<String, List<UrlResult>> e : result.entrySet()) {
            for (UrlResult resultValue : e.getValue()) {
                System.out.println(e.getKey() + resultValue);
            }
        }

    }

}
