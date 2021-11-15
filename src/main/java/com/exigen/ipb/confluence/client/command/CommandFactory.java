/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.exigen.ipb.confluence.client.command;

import com.exigen.ipb.confluence.client.command.impl.*;
import com.exigen.ipb.confluence.client.config.Configuration;

/**
 * Factory class to encapsulate client command creation
 *
 * @autor esagan on 2/4/2016.
 * @version 1.0
 */
public final class CommandFactory {

    private CommandFactory(){}

    /**
     * Returns {@link com.exigen.ipb.confluence.client.command.impl.FindExternalLinksCommand FindExternalLinksCommand}
     *
     * @param configuration {@link com.exigen.ipb.confluence.client.config.Configuration Configuration}
     * @return {@link com.exigen.ipb.confluence.client.command.Command Command}
     */
    public static Command getFindExternalLinksCommand(Configuration configuration){
        return new FindExternalLinksCommand(configuration);
    }

    /**
     * Returns {@link com.exigen.ipb.confluence.client.command.impl.DeleteChildPagesCommand DeleteChildPagesCommand}
     *
     * @param configuration {@link com.exigen.ipb.confluence.client.config.Configuration Configuration}
     * @return {@link com.exigen.ipb.confluence.client.command.Command Command}
     */
    public static Command getDeleteChildPagesCommand(Configuration configuration){
        return new DeleteChildPagesCommand(configuration);
    }

    /**
     * Returns {@link com.exigen.ipb.confluence.client.command.impl.GenerateReferencesCommand GenerateReferencesCommand}
     *
     * @param configuration {@link com.exigen.ipb.confluence.client.config.Configuration Configuration}
     * @return {@link com.exigen.ipb.confluence.client.command.Command Command}
     */
    public static Command getGenerateReferencesCommand(Configuration configuration){
        return new GenerateReferencesCommand(configuration);
    }

    /**
     * Returns {@link com.exigen.ipb.confluence.client.command.impl.ChangeExternalImgLinkWithAttachmentsCommand ChangeExternalImgLinkWithAttachmentsCommand}
     *
     * @param configuration {@link com.exigen.ipb.confluence.client.config.Configuration Configuration}
     * @return {@link com.exigen.ipb.confluence.client.command.Command Command}
     */
    public static Command getChangeExtImgLinkWithAttachmentsCommand(Configuration configuration){
        return new ChangeExternalImgLinkWithAttachmentsCommand(configuration);
    }

    /**
     * Returns {@link com.exigen.ipb.confluence.client.command.impl.UpdatePageTitlesWithPrefixCommand UpdatePageTitlesWithPrefixCommand}
     *
     * @param configuration {@link com.exigen.ipb.confluence.client.config.Configuration Configuration}
     * @return {@link com.exigen.ipb.confluence.client.command.Command Command}
     */
    public static Command getUpdatePageTitleWithPrefixCommand(Configuration configuration){
        return new UpdatePageTitlesWithPrefixCommand(configuration);
    }

    /**
     * Returns {@link com.exigen.ipb.confluence.client.command.impl.UploadImgFilesToConfluenceCommand UploadImgFilesToConfluenceCommand}
     *
     * @param configuration {@link com.exigen.ipb.confluence.client.config.Configuration Configuration}
     * @return {@link com.exigen.ipb.confluence.client.command.Command Command}
     */
    public static Command getUploadImgFilesToConfluenceCommand(Configuration configuration){
        return new UploadImgFilesToConfluenceCommand(configuration);
    }

    /**
     * Returns {@link com.exigen.ipb.confluence.client.command.impl.FindBrokenLinksCommand FindBrokenLinksCommand}
     *
     * @param configuration {@link com.exigen.ipb.confluence.client.config.Configuration Configuration}
     * @return {@link com.exigen.ipb.confluence.client.command.Command Command}
     */
    public static Command getFindBrokenLinksCommand(Configuration configuration){
        return new FindBrokenLinksCommand(configuration);
    }

}
