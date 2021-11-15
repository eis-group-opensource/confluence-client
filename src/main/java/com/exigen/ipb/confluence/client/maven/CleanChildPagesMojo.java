/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.exigen.ipb.confluence.client.maven;

import com.exigen.ipb.confluence.client.command.Command;
import com.exigen.ipb.confluence.client.command.CommandFactory;
import com.exigen.ipb.confluence.client.command.impl.DeleteChildPagesCommandCtx;
import com.exigen.ipb.confluence.client.exception.EisConfluenceException;
import com.exigen.ipb.confluence.client.util.MavenLoggerWrapper;
import com.google.common.base.Strings;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 *  Maven Mojo wrapper
 *  for client's command - DeleteChildPagesCommand {@link com.exigen.ipb.confluence.client.command.impl.DeleteChildPagesCommand}
 *  </p>
 *  use followed command to invoke this goal from command line
 *  <p>
 *  mvn maven-eisconfluence-plugin:clean-child-pages -N
 * </p>
 * <p>
 *  in case if the plugin will be invoked with command line argument "listOfPages" -
 *  in this case provided pages and their childs will be deleted.
 *  </p>
 *  use followed command
 *  <p>
 *  mvn maven-eisconfluence-plugin:clean-child-pages -DlistOfPages="page1,page2,page3" -N
 *  </p>
 *  the pages which names are equal to prefix + " " + page1 or
 *  prefix + " " + page2 and so on and their childs will be deleted.
 *
 *
 * @autor esagan on 2/4/2016.
 * @version 1.0
 */
@Mojo(name = "clean-child-pages", defaultPhase = LifecyclePhase.NONE,aggregator = true)
public class CleanChildPagesMojo  extends AbstractClientMojo {

    private static final String LIST_OF_PAGES_PROPERTIES = "listOfPages";
    private static final String PAGES_SEPARATION_CHAR = ",";

    public void execute() throws MojoExecutionException {
        getLog().info("-- clean-child-pages goal started --");

        List<String> listOfPagesArgument = getListOfPagesArgument();
        try {
            Command command = CommandFactory.getDeleteChildPagesCommand(getConfiguraiton());
            if (listOfPagesArgument.isEmpty()) {
                command.execute();
            } else {
                command.execute(new DeleteChildPagesCommandCtx(new MavenLoggerWrapper(getLog()),listOfPagesArgument));
            }
        } catch (EisConfluenceException e) {
            throw new MojoExecutionException(e.getMessage(), e);
        }
        getLog().info("-- clean-child-pages goal ended --");
    }

    private List<String> getListOfPagesArgument(){
        String property = System.getProperty(LIST_OF_PAGES_PROPERTIES);

        if (Strings.isNullOrEmpty(property) || Strings.isNullOrEmpty(property.trim())){
            getLog().info("-- clean-child-pages command line argument:"+ LIST_OF_PAGES_PROPERTIES + " is null or empty --");
            return Collections.<String>emptyList();
        }

        ArrayList<String> pageNames = new ArrayList<>();
        String[] split = property.trim().split(PAGES_SEPARATION_CHAR);
        for (String s : split){
            String trimedString = s.trim();
            if (!Strings.isNullOrEmpty(trimedString)){
                pageNames.add(trimedString);
            }
        }

        return pageNames;
    }
}
