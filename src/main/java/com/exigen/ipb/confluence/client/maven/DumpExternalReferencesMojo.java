/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.exigen.ipb.confluence.client.maven;

import com.exigen.ipb.confluence.client.command.Command;
import com.exigen.ipb.confluence.client.command.CommandFactory;
import com.exigen.ipb.confluence.client.command.impl.FindExternalLinksCommand;
import com.exigen.ipb.confluence.client.exception.EisConfluenceException;
import com.exigen.ipb.confluence.client.model.Page;
import org.apache.commons.collections.MapUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

import java.util.List;
import java.util.Map;

/**
 *  Maven Mojo wrapper
 *  for client's command -  FindExternalLinksCommand {@link com.exigen.ipb.confluence.client.command.impl.FindExternalLinksCommand}
 *  use followed command to invoke this goal from command line
 *  mvn maven-eisconfluence-plugin:dump-external-references -N
 *
 * @autor esagan on 2/4/2016.
 * @version 1.0
 */
@Mojo(name = "dump-external-references", defaultPhase = LifecyclePhase.NONE,aggregator = true)
public class DumpExternalReferencesMojo extends AbstractClientMojo {
    @Override
    public void execute() throws MojoExecutionException {
        getLog().info("-- dump-external-references goal started --");

        try {
            Command<Map<Page, List<String>>,Void> command = CommandFactory.getFindExternalLinksCommand(getConfiguraiton());

            Map<Page, List<String>> result = command.execute();
            if (!MapUtils.isEmpty(result)) {
                for (Map.Entry<Page, List<String>> e : result.entrySet()) {
                    getLog().info("for page with title: '" + e.getKey().getTitle()+"'");
                    for (String link : e.getValue()) {
                        getLog().info(link);
                    }
                }

            }

        } catch (EisConfluenceException e) {
            throw new MojoExecutionException(e.getMessage(), e);
        }
        getLog().info("-- dump-external-references goal ended --");
    }

}
