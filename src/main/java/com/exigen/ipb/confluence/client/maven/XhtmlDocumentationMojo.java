/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.exigen.ipb.confluence.client.maven;

import com.exigen.ipb.confluence.client.parser.xhtml.PageCreationUtils;
import com.exigen.ipb.confluence.client.parser.xhtml.PageInfo;
import com.exigen.ipb.confluence.client.parser.xhtml.TagType;
import com.exigen.ipb.confluence.client.parser.xhtml.XhtmlScanner;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.codehaus.plexus.util.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * This is maven plugin implementation used for documentation generation from xhtml files.
 *
 *  use followed command to invoke this goal from command line
 *  mvn maven-eisconfluence-plugin:aggregate-xhtml
 *
 * @author dstulgis
 * @author astasauskas
 */
@Mojo(inheritByDefault = false,
        name = "aggregate-xhtml",
        aggregator = true,
        defaultPhase = LifecyclePhase.NONE)
public class XhtmlDocumentationMojo extends AbstractClientMojo {

    private static final String LINK_PAGE_NAME = "links.html";
    private static final String MENU_PAGE_REF = "xhtmlDocs.html";

    @Parameter(property = "basedir", defaultValue = "${basedir}")
    private String _basedir;

    public void execute() throws MojoExecutionException {
        getLog().info("-- aggregate-xhtml goal started --");
        getLog().info("Starting directory:" + _basedir);

        getLog().info("Generating documentation for " + (getConfiguraiton().getInputPublicDoc() ? "public" : "internal") + " xhtml files.");

        Map<String, PageInfo> scannedFiles = null;
        try {
            scannedFiles = XhtmlScanner.scan(new File(_basedir), getLog(), getConfiguraiton().getInputSkipDirs());//getskipDirs);
        } catch (RuntimeException e) {
            throw new MojoExecutionException(e.getMessage(), e);
        }

        scannedFiles = new TreeMap<String, PageInfo>(scannedFiles);
        generateMenuPage(getConfiguraiton().getInputRootFolder());
        generateLinksPage(scannedFiles, getConfiguraiton().getInputRootFolder(), getConfiguraiton().getInputPublicDoc());

        if (!getConfiguraiton().getInputPublicDoc() || (getConfiguraiton().getInputPublicDoc() && containsPublicPages(scannedFiles))) {

            try {
                generateDocumentationPages(scannedFiles, getConfiguraiton().getInputRootFolder(), getConfiguraiton().getInputPublicDoc());
            } catch (RuntimeException e) {
                throw new MojoExecutionException(e.getMessage(), e);
            }
        }
        getLog().info("-- aggregate-xhtml goal ended --");
    }

    private boolean containsPublicPages(Map<String, PageInfo> scannedFiles) {
        for (PageInfo pageInfo : scannedFiles.values()) {
            if (pageInfo.isPublicInfoHolder()) {
                return true;
            }
        }
        return false;
    }

    private void generateMenuPage(String targetDir) throws MojoExecutionException {
        File menuPageFile = new File(targetDir + File.separatorChar + MENU_PAGE_REF);

        try {
            menuPageFile.getParentFile().mkdirs();
            menuPageFile.createNewFile();
        } catch (IOException e) {
            throw new MojoExecutionException("Failed creating xhtml documentation site pages.", e);
        }


        try {
            String menuPageContents = PageCreationUtils.createFramesPage(LINK_PAGE_NAME);
            FileUtils.fileWrite(menuPageFile, menuPageContents);
        } catch (IOException e) {
            throw new MojoExecutionException("Failed creating menu page.", e);
        }
    }

    private void generateLinksPage(Map<String, PageInfo> scannedFiles, String targetDir, Boolean isPublicDoc) throws MojoExecutionException {
        File linksPageFile = new File(targetDir + File.separatorChar + LINK_PAGE_NAME);

        try {
            linksPageFile.getParentFile().mkdirs();
            linksPageFile.createNewFile();
        } catch (IOException e) {
            throw new MojoExecutionException("Failed creating xhtml documentation site pages.", e);
        }
        Map<String, PageInfo> nonExistingPages = new HashMap<String, PageInfo>();
        for (PageInfo pageInfo : scannedFiles.values()) {
            for (PageInfo innerPageInfo : pageInfo.getIncludedInPage()) {
                String key;
                if (TagType.ui_decorate.equals(innerPageInfo.getTagType())) {
                    key = PageCreationUtils.transformPathToKey(innerPageInfo.getTemplate());
                } else {
                    key = PageCreationUtils.transformPathToKey(innerPageInfo.getRelativePath());
                }
                key = XhtmlScanner.handleElExAndEmptySrc(key, false);
                PageInfo existingPage = scannedFiles.get(key);
                if (existingPage == null) {
                    innerPageInfo.setNonExistantPage(true);
                    nonExistingPages.put(key, innerPageInfo);
                }
            }
        }
        scannedFiles.putAll(nonExistingPages);


        try {
            String linksPageContents = PageCreationUtils.createLinksPage(scannedFiles, isPublicDoc);
            FileUtils.fileWrite(linksPageFile, linksPageContents);
        } catch (IOException e) {
            throw new MojoExecutionException("Failed creating links page.", e);
        }
    }

    private void generateDocumentationPages(Map<String, PageInfo> scannedFiles, String targetDir, Boolean isPublicDoc) {
        PageCreationUtils.createPages(targetDir, scannedFiles, isPublicDoc);
    }

}
