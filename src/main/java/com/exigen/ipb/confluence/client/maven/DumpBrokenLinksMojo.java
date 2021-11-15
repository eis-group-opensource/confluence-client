/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.exigen.ipb.confluence.client.maven;

import com.exigen.ipb.confluence.client.command.Command;
import com.exigen.ipb.confluence.client.command.CommandFactory;
import com.exigen.ipb.confluence.client.exception.EisConfluenceException;
import com.exigen.ipb.confluence.client.parser.UrlResult;
import org.apache.commons.collections.MapUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

import java.util.List;
import java.util.Map;

/**
 *  Maven Mojo wrapper
 *  for client's command -  FindBrokenLinksCommand {@link com.exigen.ipb.confluence.client.command.impl.FindBrokenLinksCommand}
 *  use followed command to invoke this goal from command line
 *  mvn maven-eisconfluence-plugin:dump-broken-links -N
 *
 * @autor esagan
 * @version 1.0
 */
@Mojo(name = "dump-broken-links", defaultPhase = LifecyclePhase.NONE,aggregator = true)
public class DumpBrokenLinksMojo extends AbstractClientMojo {
    @Override
    public void execute() throws MojoExecutionException {
        getLog().info("-- dump-broken-links goal started --");

        try {
            Command<Map<String, List<UrlResult>>,Void> command = CommandFactory.getFindBrokenLinksCommand(getConfiguraiton());

            Map<String, List<UrlResult>> result = command.execute();
            if (!MapUtils.isEmpty(result)) {
                for (Map.Entry<String, List<UrlResult>> e : result.entrySet()) {
                    for (UrlResult resultValue : e.getValue()) {
                        getLog().info(e.getKey() +":"+ resultValue);
                    }
                }

            }

        } catch (EisConfluenceException e) {
            throw new MojoExecutionException(e.getMessage(), e);
        }
        getLog().info("-- dump-broken-links goal ended --");
    }

}
