/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.exigen.ipb.confluence.client.config.impl;

import com.exigen.ipb.confluence.client.config.Configuration;
import com.exigen.ipb.confluence.client.util.ClientConstants;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Strings;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.maven.plugins.annotations.Parameter;

import java.util.Collection;
import java.util.List;

/**
 * Eis confluence client configuration implementation
 *
 * @autor esagan on 1/18/2016.
 * @version 1.0
 */
public class ClientConfiguration implements Configuration {

    private ConfluenceConfiguration confluenceConfiguration;
    private InputConfiguration inputConfiguration;

    private ClientConfiguration(){}

    /**
     * {@inheritDoc}
     */
    @Override
    public String getInputRootFile() {
        return inputConfiguration.getDocumentationRootFile();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getInputRootFolder() {
        return inputConfiguration.getDocumentationRootFolder();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getInputFileEncoding() {
        return inputConfiguration.getFileEncoding();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getConfluenceServerURI() {
        return confluenceConfiguration.getServerURI();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getConfluenceUserName() {
        return confluenceConfiguration.getUserName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getConfluenceUserPassword() {
        return confluenceConfiguration.getPassword();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getConfluenceDefaultSpace() {
        return confluenceConfiguration.getSpaceKey();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getConfluenceRootPageTitle() {
        return confluenceConfiguration.getRootPageTitle();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getConfluencePagePrefix() {
        return confluenceConfiguration.getPagePrefix();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getInputInclusiveLinks() {
        return ImmutableList.copyOf(inputConfiguration.getInclusiveLinks());
    }

    @Override
    public List<String> getInputSkipDirs() {
        return ImmutableList.copyOf(inputConfiguration.getSkipDirs());
    }

    @Override
    public Boolean getInputPublicDoc() {
        return inputConfiguration.getPublicDoc();
    }

    /**
     * Builder class to generate {@link Configuration} instance
     */
    public static class Builder {

        private ConfluenceConfiguration nestedConfluenceConfiguration;
        private InputConfiguration nestedInputConfiguration;

        public Builder withConfluenceConfiguration(ConfluenceConfiguration confluenceConfiguration){
            this.nestedConfluenceConfiguration = confluenceConfiguration;
            return this;
        }

        public Builder withInputConfiguration(InputConfiguration inputConfiguration){
            this.nestedInputConfiguration = inputConfiguration;
            return this;
        }

        public Configuration build(){
            ClientConfiguration clientConfiguration = new ClientConfiguration();
            //init confluence client configuration
            ConfluenceConfiguration confluenceConfiguration = null;
            if (nestedConfluenceConfiguration != null) {
                confluenceConfiguration = nestedConfluenceConfiguration;
            } else {
                confluenceConfiguration = new ConfluenceConfiguration();
            }

            clientConfiguration.confluenceConfiguration = confluenceConfiguration;
            //init input configuration
            InputConfiguration inputConfiguration;
            if (nestedInputConfiguration == null){
                inputConfiguration = new InputConfiguration();
            } else {
                inputConfiguration = nestedInputConfiguration;
            }

            clientConfiguration.inputConfiguration = inputConfiguration;

            return clientConfiguration;
        }

    }

}
