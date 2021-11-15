/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.exigen.ipb.confluence.client.maven;

import com.exigen.ipb.confluence.client.command.Command;
import com.exigen.ipb.confluence.client.command.CommandFactory;
import com.exigen.ipb.confluence.client.exception.EisConfluenceException;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

/**
 *  Maven Mojo wrapper
 *  for client's command - GenerateReferencesCommand {@link com.exigen.ipb.confluence.client.command.impl.GenerateReferencesCommand}
 *  use followed command to invoke this goal from command line
 *  mvn maven-eisconfluence-plugin:generate-references -N
 *
 * @author esagan
 * @version 1.0
 */
@Mojo(name = "generate-references", defaultPhase = LifecyclePhase.NONE,aggregator = true)
public class GenerateReferencesMojo extends AbstractClientMojo {

    public void execute() throws MojoExecutionException {
        getLog().info("-- generate-references goal started --");

        try {
            Command generateReferencesCommand = CommandFactory.getGenerateReferencesCommand(getConfiguraiton());
            generateReferencesCommand.execute();
        } catch (EisConfluenceException e) {
            throw new MojoExecutionException(e.getMessage(), e);
        }
        getLog().info("-- generate-references goal ended --");
    }
}
