/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.exigen.ipb.confluence.client.maven;

import com.exigen.ipb.confluence.client.config.Configuration;
import com.exigen.ipb.confluence.client.config.impl.ClientConfiguration;
import com.exigen.ipb.confluence.client.config.impl.ConfluenceConfiguration;
import com.exigen.ipb.confluence.client.config.impl.InputConfiguration;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * Base class for eis confluence client maven mojo.
 * It keeps common command logic and state.
 * @see <a href="http://maven.apache.org/developers/mojo-api-specification.html">Mojo API Specification</a>
 *
 * @autor esagan on 2/4/2016.
 * @version 1.0
 */
public abstract class AbstractClientMojo extends AbstractMojo {

    @Parameter(required = true)
    private ConfluenceConfiguration confluenceConfiguration;

    @Parameter
    private InputConfiguration inputConfiguration;

    protected Configuration getConfiguraiton(){

        if (inputConfiguration == null) {
            getLog().warn("!!! inputConfiguration is null !!!");
        }

        if (getLog().isDebugEnabled()){
            getLog().debug("inputConfiguration: " + inputConfiguration);
            getLog().debug("confluenceConfiguration: " + confluenceConfiguration);
        }
        return new ClientConfiguration.Builder()
                    .withConfluenceConfiguration(confluenceConfiguration)
                    .withInputConfiguration(inputConfiguration)
                .build();
    }

}
