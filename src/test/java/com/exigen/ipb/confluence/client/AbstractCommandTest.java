/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.exigen.ipb.confluence.client;

import com.exigen.ipb.confluence.client.config.Configuration;
import com.exigen.ipb.confluence.client.config.impl.ClientConfiguration;
import com.exigen.ipb.confluence.client.config.impl.ConfluenceConfiguration;

/**
 * @autor esagan on 4/7/2016.
 * @since 1.0
 */
public abstract class AbstractCommandTest {

    protected Configuration getConfiguration() {
        ConfluenceConfiguration confluenceConfiguration = new ConfluenceConfiguration();
        confluenceConfiguration.setServerURI("https://wiki.exigeninsurance.com/");
        confluenceConfiguration.setUserName("test_user");
        confluenceConfiguration.setPassword("testPass");
        confluenceConfiguration.setRootPageTitle("[TestSys] Test Root Page");
        confluenceConfiguration.setPagePrefix("[TestSys]");
        confluenceConfiguration.setSpaceKey("~test_space");
        return new ClientConfiguration.Builder()
                .withConfluenceConfiguration(confluenceConfiguration)
                .build();
    }
}
