/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.exigen.ipb.confluence.client.impl.xmlrpc;

import com.exigen.ipb.confluence.client.config.Configuration;
import com.exigen.ipb.confluence.client.impl.ConfluenceClient;
import com.exigen.ipb.confluence.client.exception.EisConfluenceException;
import com.exigen.ipb.confluence.client.model.*;
import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import static com.exigen.ipb.confluence.client.impl.xmlrpc.utils.ObjectUtils.mapObject;
import static com.exigen.ipb.confluence.client.impl.xmlrpc.utils.ObjectUtils.mapObjects;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Confluence XML RPC client implementation
 * @see <a href="http://developer.atlassian.com/confdev/confluence-rest-api/confluence-xml-rpc-and-soap-apis/remote-confluence-methods">Remote confluence methods</a>
 *
 * @author esagan on 1/16/2016.
 * @version 1.0
 */
public class XmlRpcConfluenceClient implements ConfluenceClient {

    private static final String RPC_PATH = "/rpc/xmlrpc";
    private Configuration configuration;

    private XmlRpcClient rpcClient;
    private String tocken = null;

    public XmlRpcConfluenceClient(Configuration configuration) {

        this.configuration = configuration;
        XmlRpcClientConfigImpl xmlRpcClientConfig = new XmlRpcClientConfigImpl();
        try {
            xmlRpcClientConfig.setServerURL(new URL(configuration.getConfluenceServerURI() + RPC_PATH));
            // Initialise RPC Client
            rpcClient = new XmlRpcClient();
            rpcClient.setConfig(xmlRpcClientConfig);
        } catch (MalformedURLException e) {
            throw new EisConfluenceException(e.getMessage(), e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String login() {
        try {
            this.tocken = (String) rpcClient.execute(XmlRpcSupportedCommands.LOGIN, Lists.newArrayList(configuration.getConfluenceUserName(),configuration.getConfluenceUserPassword()));
            return tocken;
        } catch (XmlRpcException e) {
            throw new EisConfluenceException(e.getMessage(), e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean logout() {
        // Log out
        boolean aBoolean = false;
        try {
            aBoolean = (Boolean) rpcClient.execute(XmlRpcSupportedCommands.LOGOUT, Lists.newArrayList(tocken));
            if (aBoolean) {
                tocken = null;
            }
            return aBoolean;
        } catch (XmlRpcException e) {
            throw new EisConfluenceException(e.getMessage(), e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page getPageById(Long id) {
        checkNotNull(id);
        try {
            Object obj = rpcClient.execute(XmlRpcSupportedCommands.GET_PAGE, Lists.newArrayList(tocken,id.toString()));
            return mapObject(obj, Page.class);
        } catch (XmlRpcException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new EisConfluenceException(e.getMessage(), e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page getPageBySpaceAndTitle(String spaceKey, String pageTitle){
        Object obj;
        try {
            obj = rpcClient.execute(XmlRpcSupportedCommands.GET_PAGE, Lists.newArrayList(tocken, spaceKey, pageTitle));
            return mapObject(obj, Page.class);
        } catch (XmlRpcException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new EisConfluenceException(e.getMessage(),e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PageSummary> getPages(String spaceKey) {
        Object[] obj;
        try {
            obj = (Object[]) rpcClient.execute(XmlRpcSupportedCommands.GET_PAGES, Lists.newArrayList(tocken, spaceKey));
            return mapObjects(obj, PageSummary.class);
        } catch (XmlRpcException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new EisConfluenceException(e.getMessage(),e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page storePage(Page page) {

        Object obj;
        System.out.print("------ Print page ---------");
        System.out.println(page.toString());
        try {
            Map objectAsMap = Maps.filterEntries(BeanUtils.describe(page), new Predicate<Map.Entry<String, String>>() {
                @Override
                public boolean apply(Map.Entry<String, String> stringStringEntry) {
                    return !(stringStringEntry.getValue() == null || stringStringEntry.getValue() == "null");
                }
            });
            obj = rpcClient.execute(XmlRpcSupportedCommands.STORE_PAGE, Lists.newArrayList(tocken, objectAsMap));
            return mapObject(obj, Page.class);
        } catch (NoSuchMethodException | XmlRpcException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            System.out.println("Cannot save page " + page.getTitle());
            System.out.println(e.toString());
            //throw new EisConfluenceException(e.getMessage(),e);
            return null;
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removePage(String pageId) {
        try {
            rpcClient.execute(XmlRpcSupportedCommands.REMOVE_PAGE, Lists.newArrayList(tocken, pageId));
        } catch (XmlRpcException e) {
            throw new EisConfluenceException(e.getMessage(),e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void movePage(String sourcePageId, String targetPageId, String position) {
        try {
            rpcClient.execute(XmlRpcSupportedCommands.MOVE_PAGE, Lists.newArrayList(tocken, sourcePageId,targetPageId, position));
        } catch (XmlRpcException e) {
            throw new EisConfluenceException(e.getMessage(),e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SpaceSummary> getSpaces() {
        Object[] obj;
        try {
            obj = (Object[]) rpcClient.execute(XmlRpcSupportedCommands.GET_SPACES, Lists.newArrayList(tocken));
            return mapObjects(obj, SpaceSummary.class);
        } catch (XmlRpcException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new EisConfluenceException(e.getMessage(),e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Space getSpace(String spaceKey){
        Object obj;
        try {
            obj = rpcClient.execute(XmlRpcSupportedCommands.GET_SPACE, Lists.newArrayList(tocken, spaceKey));
            return mapObject(obj, Space.class);
        } catch (XmlRpcException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new EisConfluenceException(e.getMessage(),e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ServerInfo getServerInfo(){
        Object obj;
        try {
            obj = rpcClient.execute(XmlRpcSupportedCommands.GET_SERVER_INFO, Lists.newArrayList(tocken));
            return mapObject(obj, ServerInfo.class);
        } catch (XmlRpcException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new EisConfluenceException(e.getMessage(),e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String convertWikiToStorageFormat(String markUp){
        try {
            return (String) rpcClient.execute(XmlRpcSupportedCommands.CONVERT_WIKI_TO_STORAGE_FORMAT, Lists.newArrayList(tocken, markUp));
        } catch (XmlRpcException e) {
            throw new EisConfluenceException(e.getMessage(),e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Attachment> getAttachments(String pageId) {
        Object[] obj;
        try {
            obj = (Object[]) rpcClient.execute(XmlRpcSupportedCommands.GET_ATTACHMENTS, Lists.newArrayList(tocken,pageId));
            return mapObjects(obj, Attachment.class);
        } catch (XmlRpcException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new EisConfluenceException(e.getMessage(),e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Attachment getAttachment(String pageId, String fileName, String versionNumber) {
        Object obj;
        try {
            obj = rpcClient.execute(XmlRpcSupportedCommands.GET_ATTACHMENT, Lists.newArrayList(tocken,pageId,fileName,versionNumber));
            return mapObject(obj, Attachment.class);
        } catch (XmlRpcException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new EisConfluenceException(e.getMessage(),e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Attachment addAttachment(String contentId, Attachment attachment, byte[] attachmentData) {
        Object obj;
        try {
            Map objectAsMap = Maps.filterEntries(BeanUtils.describe(attachment), new Predicate<Map.Entry<String, String>>() {
                @Override
                public boolean apply(Map.Entry<String, String> stringStringEntry) {
                    return !(stringStringEntry.getValue() == null || stringStringEntry.getValue() == "null");
                }
            });
            obj =  rpcClient.execute(XmlRpcSupportedCommands.ADD_ATTACHMENT, Lists.newArrayList(tocken,contentId,objectAsMap,attachmentData));
            return mapObject(obj, Attachment.class);
        } catch (XmlRpcException | InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            throw new EisConfluenceException(e.getMessage(), e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeAttachment(String contentId, String fileName) {
        try {
            return (boolean) rpcClient.execute(XmlRpcSupportedCommands.REMOVE_ATTACHMENT, Lists.newArrayList(tocken, contentId, fileName));
        } catch (XmlRpcException e) {
            throw new EisConfluenceException(e.getMessage(), e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean moveAttachment(String originalContentId, String originalName, String newContentEntityId, String newName) {
        try {
            return (boolean) rpcClient.execute(XmlRpcSupportedCommands.MOVE_ATTACHMENT, Lists.newArrayList(tocken, originalContentId, originalName,newContentEntityId,newName));
        } catch (XmlRpcException e) {
            throw new EisConfluenceException(e.getMessage(), e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] getAttachmentData(String pageId, String fileName, String versionNumber) {
        try {
            return (byte[]) rpcClient.execute(XmlRpcSupportedCommands.GET_ATTACHMENT_DATA, Lists.newArrayList(tocken, pageId, fileName, versionNumber));
        } catch (XmlRpcException e) {
            throw new EisConfluenceException(e.getMessage(),e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PageSummary> getAncestors(String pageId) {
        Object[] obj;
        try {
            obj = (Object[]) rpcClient.execute(XmlRpcSupportedCommands.GET_ANCESTORS, Lists.newArrayList(tocken,pageId));
            return mapObjects(obj, PageSummary.class);
        } catch (XmlRpcException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new EisConfluenceException(e.getMessage(),e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PageSummary> getDescendents(String pageId) {
        Object[] obj;
        try {
            obj = (Object[]) rpcClient.execute(XmlRpcSupportedCommands.GET_DESCENDENTS, Lists.newArrayList(tocken,pageId));
            return mapObjects(obj, PageSummary.class);
        } catch (XmlRpcException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new EisConfluenceException(e.getMessage(),e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PageSummary> getChildren(String pageId) {
        Object[] obj;
        try {
            obj = (Object[]) rpcClient.execute(XmlRpcSupportedCommands.GET_CHILDREN, Lists.newArrayList(tocken,pageId));
            return mapObjects(obj, PageSummary.class);
        } catch (XmlRpcException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new EisConfluenceException(e.getMessage(),e);
        }
    }

}
