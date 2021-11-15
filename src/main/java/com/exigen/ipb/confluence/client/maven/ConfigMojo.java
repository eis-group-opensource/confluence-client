/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.exigen.ipb.confluence.client.maven;

import com.exigen.ipb.confluence.client.util.ClientConstants;
import com.google.common.io.CharStreams;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *  Maven Mojo to display plugin configuration with command from command line
 *  mvn maven-eisconfluence-plugin:help-config
 *
 * @autor esagan on 2/4/2016.
 * @version
 */
@Mojo(name = "help-config", defaultPhase = LifecyclePhase.NONE, aggregator = true)
public class ConfigMojo extends AbstractMojo {

    private static final String SAMPLE_CONFIGURATION_RESOURCE = "/sample-plugin-configuration.xml";
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {

        InputStream resourceAsStream = null;
        try {
            resourceAsStream = this.getClass().getResourceAsStream(SAMPLE_CONFIGURATION_RESOURCE);
            getLog().info(CharStreams.toString(new InputStreamReader(resourceAsStream, ClientConstants.FILE_ENCODING)));
        } catch (IOException e) {
            getLog().error(e.getMessage(), e);
            throw new MojoExecutionException(e.getMessage(),e);
        } finally {
            if (resourceAsStream != null) {
                try {
                    resourceAsStream.close();
                } catch (IOException e) {
                    getLog().error(e.getMessage(), e);
                }
            }
        }
    }


}
