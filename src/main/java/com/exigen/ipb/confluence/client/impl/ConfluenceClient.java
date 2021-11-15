/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.exigen.ipb.confluence.client.impl;

import com.exigen.ipb.confluence.client.model.*;

import java.util.List;

/**
 * Confluence client low-level API
 *
 * @author esagan on 1/16/2016.
 * @version 1.0
 */
public interface ConfluenceClient {

    /**
     * log in a user.
     *
     * @return String - confluence token
     * which is used in other commands for authorization purpuses
     * @throws com.exigen.ipb.confluence.client.exception.EisConfluenceException
     */
    String login();

    /**
     * log out a user.
     *
     * @return boolean - boolean indicator to show was logout successfull or not
     * @throws com.exigen.ipb.confluence.client.exception.EisConfluenceException
     */
    boolean logout();

    /**
     * Returns confluence page
     *
     * @param id all confluence pages has internal id and every page can be
     *             identified with it. {@link com.exigen.ipb.confluence.client.model.Page#id}
     * @return Page - confluence page {@link com.exigen.ipb.confluence.client.model.Page Page}
     * @throws com.exigen.ipb.confluence.client.exception.EisConfluenceException
     */
    Page getPageById(Long id);

    /**
     * Returns confluence page
     *
     * @param spaceKey confluence space key {@link com.exigen.ipb.confluence.client.model.Space#key}
     * @param pageTitle confluence page title {@link com.exigen.ipb.confluence.client.model.Page#title}
     * @return Page confluence page {@link com.exigen.ipb.confluence.client.model.Page Page}
     * @throws com.exigen.ipb.confluence.client.exception.EisConfluenceException
     */
    Page getPageBySpaceAndTitle(String spaceKey, String pageTitle);

    /**
     * Returns pages summary existed in requested confluence space
     *
     * @param spaceKey confluence space key {@link com.exigen.ipb.confluence.client.model.Space#key}
     * @return List<PageSummary> {@link com.exigen.ipb.confluence.client.model.PageSummary}
     * @throws com.exigen.ipb.confluence.client.exception.EisConfluenceException
     */
    List<PageSummary> getPages(String spaceKey);

    /**
     * Creates or updates page.
     * If page has pageId it will be updated, if not - created
     *
     * @param page confluence page {@link com.exigen.ipb.confluence.client.model.Page Page}
     * @return updated {@link com.exigen.ipb.confluence.client.model.Page Page} object
     * @throws com.exigen.ipb.confluence.client.exception.EisConfluenceException
     */
    Page storePage(Page page);

    /**
     * Deletes page from confluence by pageId
     *
     * @param pageId all confluence pages has internal id and every page can be
     *             identified with it. @see {@link com.exigen.ipb.confluence.client.model.Page#id}
     * @throws com.exigen.ipb.confluence.client.exception.EisConfluenceException
     */
    void removePage(String pageId);

    /**
     * Moves a page's position in the hierarchy.
     *
     * @param sourcePageId the id of the page to be moved.
     * @param targetPageId the id of the page that is relative to the sourcePageId page being moved.
     * @param position "above`", "below", or "append".
     *                  (Note that the terms 'above' and 'below' refer to the relative vertical position of the pages in the page tree.)
     * <P>Position Keys for Moving a Page</P>
     * <table border="1">
     *   <tr><th>Position Key</th><th>Effect</th></tr>
     *   <tr><td>above</td><td>source and target become/remain sibling pages and the source is moved above the target in the page tree.</td></tr>
     *   <tr><td>below</td><td>source and target become/remain sibling pages and the source is moved below the target in the page tree.</td></tr>
     *   <tr><td>append</td><td>source becomes a child of the target</td></tr>
     * </table>
     * @throws com.exigen.ipb.confluence.client.exception.EisConfluenceException
     */
    void movePage(String sourcePageId, String targetPageId, String position);

    /**
     * Returns all confluence space summaries existed on server and available for user.
     *
     * @return List<SpaceSummary> {@link com.exigen.ipb.confluence.client.model.SpaceSummary}
     * @throws com.exigen.ipb.confluence.client.exception.EisConfluenceException
     */
    List<SpaceSummary> getSpaces();

    /**
     * Returns confluence space information
     *
     * @param spaceKey confluence space key {@link com.exigen.ipb.confluence.client.model.Space#key}
     * @return {@link com.exigen.ipb.confluence.client.model.Space Space}
     * @throws com.exigen.ipb.confluence.client.exception.EisConfluenceException
     */
    Space getSpace(String spaceKey);

    /**
     * Returns server information about current confluence server
     *
     * @return ServerInfo {@link com.exigen.ipb.confluence.client.model.ServerInfo}
     * @throws com.exigen.ipb.confluence.client.exception.EisConfluenceException
     */
    ServerInfo getServerInfo();

    /**
     * Converts old wiki representation to new confluence storage format
     * {@link https://confluence.atlassian.com/doc/confluence-storage-format-790796544.html}
     *
     * @param markUp
     * @return String
     * @throws com.exigen.ipb.confluence.client.exception.EisConfluenceException
     */
    String convertWikiToStorageFormat(String markUp);

    /**
     * Returns resources attached to the page
     *
     * @param pageId
     * @return List<Attachment> {@link com.exigen.ipb.confluence.client.model.Attachment Attachment}
     * @throws com.exigen.ipb.confluence.client.exception.EisConfluenceException
     */
    List<Attachment> getAttachments(String pageId);

    /**
     * Returns specific version of attached to page resource
     *
     * @param pageId {@link com.exigen.ipb.confluence.client.model.Page#id}
     * @param fileName resource file name @see {@link com.exigen.ipb.confluence.client.model.Attachment#fileName}
     * @param versionNumber version number of resource, in case if value - 0 - it means current version
     * @return {@link com.exigen.ipb.confluence.client.model.Attachment Attachment}
     * @throws com.exigen.ipb.confluence.client.exception.EisConfluenceException
     */
    Attachment getAttachment(String pageId, String fileName, String versionNumber);

    /**
     * Adds resource to page as attachment
     *
     * @param contentId {@link com.exigen.ipb.confluence.client.model.Page#id}
     * @param attachment {@link com.exigen.ipb.confluence.client.model.Attachment Attachment}
     * @param attachmentData byte array with attachment content
     * @return {@link com.exigen.ipb.confluence.client.model.Attachment Attachment}
     * @throws com.exigen.ipb.confluence.client.exception.EisConfluenceException
     */
    Attachment addAttachment(String contentId, Attachment attachment, byte[] attachmentData);

    /**
     * Deletes attached to the page resource
     *
     * @param contentId {@link com.exigen.ipb.confluence.client.model.Page#id}
     * @param fileName {@link com.exigen.ipb.confluence.client.model.Attachment#fileName}
     * @return boolean - was operation successful or not
     * @throws com.exigen.ipb.confluence.client.exception.EisConfluenceException
     */
    boolean removeAttachment(String contentId, String fileName);

    /**
     * Move/rename attachment
     *
     * @param originalContentId {@link com.exigen.ipb.confluence.client.model.Page#id}
     * @param originalName {@link com.exigen.ipb.confluence.client.model.Attachment#fileName}
     * @param newContentEntityId {@link com.exigen.ipb.confluence.client.model.Page#id}
     * @param newName {@link com.exigen.ipb.confluence.client.model.Attachment#fileName}
     * @return boolean - was operation successful or not
     * @throws com.exigen.ipb.confluence.client.exception.EisConfluenceException
     */
    boolean moveAttachment(String originalContentId, String originalName, String newContentEntityId, String newName);

    /**
     * Return content of attached, to the confluence page, resource
     *
     * @param pageId {@link com.exigen.ipb.confluence.client.model.Page#id}
     * @param fileName {@link com.exigen.ipb.confluence.client.model.Attachment#fileName}
     * @param versionNumber version number of resource, in case if value - 0 - it means current version
     * @return bytes array with {@link com.exigen.ipb.confluence.client.model.Attachment Attachment} content representation
     * @throws com.exigen.ipb.confluence.client.exception.EisConfluenceException
     */
    byte[] getAttachmentData(String pageId, String fileName, String versionNumber);

    /**
     * Returns  all the ancestors of this page (parent, parent's parent etc).
     *
     * @param pageId {@link com.exigen.ipb.confluence.client.model.Page#id}
     * @return List<PageSummary> {@link com.exigen.ipb.confluence.client.model.PageSummary PageSummary}
     * @throws com.exigen.ipb.confluence.client.exception.EisConfluenceException
     */
    List<PageSummary> getAncestors(String pageId);

    /**
     * Returns all the descendants of this page (children, children's children etc).
     *
     * @param pageId {@link com.exigen.ipb.confluence.client.model.Page#id}
     * @return List<PageSummary> {@link com.exigen.ipb.confluence.client.model.PageSummary PageSummary}
     * @throws com.exigen.ipb.confluence.client.exception.EisConfluenceException
     */
    List<PageSummary> getDescendents(String pageId);

    /**
     * Returns all the direct children of this page.
     *
     * @param pageId {@link com.exigen.ipb.confluence.client.model.Page#id}
     * @return List<PageSummary> {@link com.exigen.ipb.confluence.client.model.PageSummary PageSummary}
     * @throws com.exigen.ipb.confluence.client.exception.EisConfluenceException
     */
    List<PageSummary> getChildren(String pageId);
}
