/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.exigen.ipb.confluence.client.impl.soap;

//import com.atlassian.confluence.rpc.AuthenticationFailedException;
//import com.atlassian.confluence.rpc.soap.beans.RemotePage;
//import com.exigengroup.eqx.eqxeiswiki01.plugins.servlet.soap_axis1.confluenceservice_v2.ConfluenceSoapService;
//import com.exigengroup.eqx.eqxeiswiki01.plugins.servlet.soap_axis1.confluenceservice_v2.ConfluenceSoapServiceServiceLocator;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;

/**
 * Future soap client stub in case if we will need support
 * more than one protocol to connect to confleunce
 *
 * Soap client requires generated classes from wsdl
 *
 * @author esagan on 1/15/2016.
 * @version 1.0
 */
public class PageReader {
    public static void main(String[] args) throws ServiceException, RemoteException {
//        final ConfluenceSoapService service;
//        ConfluenceSoapServiceServiceLocator serviceLocator = new ConfluenceSoapServiceServiceLocator();
//        serviceLocator.setConfluenceserviceV2EndpointAddress("https://wiki.exigeninsurance.com/rpc/soap-axis/confluenceservice-v2");
//        service = serviceLocator.getConfluenceserviceV2();
//
//        // insert your account data here
//        String token = service.login("esagan", "********");
//
//        // we are going to fetch a page from the pre-installed Demonstration
//        // Space
//        RemotePage page = service.getPage(token, "EISDOC71", "[COMMISSIONS] Introduction");
//        String renderedOutput = service.renderContent(token, "EISDOC71",
//                page.getId(), page.getContent());
//        System.out.println(page.getContent());
    }
}
