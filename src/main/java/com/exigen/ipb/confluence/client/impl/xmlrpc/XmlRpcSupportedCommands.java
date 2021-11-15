/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.exigen.ipb.confluence.client.impl.xmlrpc;

/**
 * Helper class with commands declaration and short description
 *
 * @author esagan on 1/16/2016.
 * @version 1.0
 */
public final class XmlRpcSupportedCommands {

    //Authentication Methods
    /*
     * String login(String username, String password) - log in a user.
     * Returns a String authentication token to be passed as authentication to all other remote calls.
     * Must be called before any other method in a remote conversation.
     * From 1.3 onwards, you can supply an empty string as the token to be treated as being the anonymous user.
     */
    public static final String LOGIN = "confluence2.login";
    /*
     * boolean logout(String token) - remove this token from the list of logged in tokens.
     * Returns true if the user was logged out, false if they were not logged in in the first place.
     */
    public static final String LOGOUT = "confluence2.logout";

    //Pages
    /*
     * Vector<PageSummary> getPages(String token, String spaceKey) - returns all the summaries in the space.
     * Doesn't include pages which are in the Trash.
     * Equivalent to calling Space.getCurrentPages().
     */
    public static final String GET_PAGES = "confluence2.getPages";
    /*
     * Page getPage(String token, Long pageId) - returns a single Page
     *
     * Page getPage(String token, String spaceKey, String pageTitle) - returns a single Page
     */
    public static final String GET_PAGE = "confluence2.getPage";
    /*
     * Page storePage(String token, Page  page) - adds or updates a page.
     * For adding, the Page given as an argument should have space, title and content fields at a minimum.
     * For updating, the Page given should have id, space, title, content and version fields at a minimum.
     * The parentId field is always optional. All other fields will be ignored. The content is in storage format.
     * Note: the return value can be null, if an error that did not throw an exception occurred.
     * Operates exactly like updatePage() if the page already exists.
     */
    public static final String STORE_PAGE = "confluence2.storePage";
    /*
     * void removePage(String token, String pageId) - removes a page
     */
    public static final String REMOVE_PAGE = "confluence2.removePage";
    /*
     * Vector<PageSummary> getChildren(String token, String pageId) - returns all the direct children of this page.
     */
    public static final String GET_CHILDREN = "confluence2.getChildren";
    /*
     * Vector<PageSummary> getAncestors(String token, String pageId) -
     * returns all the ancestors of this page (parent, parent's parent etc).
     */
    public static final String GET_ANCESTORS = "confluence2.getAncestors";
    /*
     * Vector<PageSummary> getDescendents(String token, String pageId) -
     * returns all the descendants of this page (children, children's children etc).
     */
    public static final String GET_DESCENDENTS = "confluence2.getDescendents";
    /*
     * Vector<Attachment> getAttachments(String token, String pageId) -
     * returns all the Attachments for this page (useful to point users to download them with the full file download URL returned).
     */
    public static final String GET_ATTACHMENTS = "confluence2.getAttachments";
    /*
     * Attachment addAttachment(String token, long contentId, Attachment attachment,
     *                          byte[] attachmentData) - add a new attachment to a content entity object.
     * Note that this uses a lot of memory - about 4 times the size of the attachment.
     * The 'long contentId' is actually a String pageId for XML-RPC. Be aware of  CONF-31169 and CONF-30024.
     */
    public static final String ADD_ATTACHMENT = "confluence2.addAttachment";
    /*
     * boolean removeAttachment(String token, String contentId, String fileName) - remove an attachment from a content entity object.
     */
    public static final String REMOVE_ATTACHMENT = "confluence2.removeAttachment";
    /*
     * boolean moveAttachment(String token, String originalContentId, String originalName, String newContentEntityId, String newName)
     * - move an attachment to a different content entity object and/or give it a new name.
     */
    public static final String MOVE_ATTACHMENT = "confluence2.moveAttachment";
    /*
     * void movePage(String token, String sourcePageId, String targetPageId, String position)- moves a page's position in the hierarchy.
            sourcePageId - the id of the page to be moved.
            targetPageId - the id of the page that is relative to the sourcePageId page being moved.
            position - "above", "below", or "append". (Note that the terms 'above' and 'below' refer to the relative vertical position of the pages in the page tree.)

        Position Keys for Moving a Page
        Position Key      Effect
        above             source and target become/remain sibling pages and the source is moved above the target in the page tree.
        below             source and target become/remain sibling pages and the source is moved below the target in the page tree.
        append            source becomes a child of the target

     */
    public static final String MOVE_PAGE = "confluence2.movePage";

    //Spaces

    /*
     * Vector<SpaceSummary> getSpaces(String token) - returns all the summaries that the current user can see.
     */
    public static final String GET_SPACES = "confluence2.getSpaces";

    /*
     * Space getSpace(String token, String spaceKey) -
     * returns a single Space. If the spaceKey does not exist: earlier versions of Confluence will throw an Exception.
     * Later versions (3.0+) will return a null object.
     */
    public static final String GET_SPACE = "confluence2.getSpace";


    //Attachments
    /*
     * Attachment getAttachment(String token, String pageId, String fileName, String versionNumber) - get information about an attachment.
     */
    public static final String GET_ATTACHMENT = "confluence2.getAttachment";

    /*
     * byte[] getAttachmentData(String token, String pageId, String fileName, String versionNumber) - get the contents of an attachment.
     */
    public static final String GET_ATTACHMENT_DATA = "confluence2.getAttachmentData";

    //General
    /*
     * ServerInfo getServerInfo(String token) - retrieve some basic information about the server being connected to.
     * Useful for clients that need to turn certain features on or off depending on the version of the server. (Since 1.0.3)
     */
    public static final String GET_SERVER_INFO = "confluence2.getServerInfo";
    /*
     * String convertWikiToStorageFormat(String token, String markup) - converts wiki markup to the storage format and returns it. (Since 4.0)
     */
    public static final String CONVERT_WIKI_TO_STORAGE_FORMAT = "confluence2.convertWikiToStorageFormat";
}
