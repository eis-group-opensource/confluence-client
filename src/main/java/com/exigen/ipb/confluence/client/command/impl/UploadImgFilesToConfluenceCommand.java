/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.exigen.ipb.confluence.client.command.impl;

import com.exigen.ipb.confluence.client.command.CommandCtx;
import com.exigen.ipb.confluence.client.config.Configuration;
import com.exigen.ipb.confluence.client.command.CommandUtils;
import com.exigen.ipb.confluence.client.exception.EisConfluenceException;
import com.exigen.ipb.confluence.client.model.Attachment;
import com.exigen.ipb.confluence.client.model.Page;
import com.exigen.ipb.confluence.client.util.ContentUtils;
import com.exigen.ipb.confluence.client.util.FileUtils;
import com.exigen.ipb.confluence.client.impl.xmlrpc.XmlRpcConfluenceClient;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * UploadImgFilesToConfluenceCommand is used to upload image files
 * from input file folder to 'root' confluence page
 * As root page for updates used - configuration.getConfluenceRootPageTitle() value
 * as input file folder is user - configuration.getInputRootFolder() + '/images' folder
 *
 * @autor esagan on 2/4/2016.
 * @version 1.0
 */
public class UploadImgFilesToConfluenceCommand extends AbstractCommand<Void,CommandCtx>{

    private static final String IMAGES_FOLDER = "/images";

    /**
     * {@inheritDoc}
     */
    public UploadImgFilesToConfluenceCommand(Configuration configuration) {
        super(configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Void execute() {
        XmlRpcConfluenceClient xmlRpcConfluenceClient = new XmlRpcConfluenceClient(configuration);
        xmlRpcConfluenceClient.login();
        Page confluencePage = xmlRpcConfluenceClient.getPageBySpaceAndTitle(configuration.getConfluenceDefaultSpace(), configuration.getConfluenceRootPageTitle());
        List<File> filesWithExtensions = FileUtils.findFilesWithExtensions(configuration.getInputRootFolder() + IMAGES_FOLDER, CommandUtils.getListOfImgFileExtensions());
        for (File file : filesWithExtensions) {
            Attachment attachment = attachFileToPage(xmlRpcConfluenceClient, confluencePage, file);
            System.out.println("attached: " + attachment.getFileName());
        }
        xmlRpcConfluenceClient.logout();
        return (null);
    }

    /**
     * Not supported by this command - throws UnsupportedOperationException
     *
     * @param input - input type
     *                in most cases it could be 'command context'
     *
     * @return Void
     */
    @Override
    public Void execute(CommandCtx input) {
        throw new UnsupportedOperationException();
    }

    private Attachment attachFileToPage(XmlRpcConfluenceClient xmlRpcConfluenceClient, Page page, File file) {
        byte[] fileContent = getFileContent(file);
        Attachment attachment = createAttachment(file.getPath(), String.valueOf(fileContent.length));
        return xmlRpcConfluenceClient.addAttachment(String.valueOf(page.getId()), attachment, fileContent);
    }

    private byte[] getFileContent(File file) {
        Path path = Paths.get(file.getPath());
        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            throw new EisConfluenceException(e.getMessage(), e);
        }
    }

    private static Attachment createAttachment(String fileName, String fileSize) {

        String mimeTypeByFileName = ContentUtils.resolveMimType(fileName);

        Path path = Paths.get(fileName);

        Attachment attachment = new Attachment();
        attachment.setFileSize(fileSize);
        attachment.setFileName(path.getFileName().toString());
        attachment.setComment(path.getFileName().toString());
        attachment.setContentType(mimeTypeByFileName);
        return attachment;
    }
}
