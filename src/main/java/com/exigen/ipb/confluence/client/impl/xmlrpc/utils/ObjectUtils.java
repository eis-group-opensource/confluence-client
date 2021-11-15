/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.exigen.ipb.confluence.client.impl.xmlrpc.utils;

import com.exigen.ipb.confluence.client.model.Page;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Utility class to map xml-rpc request and response objects
 * from map to object and vise versa
 *
 * @author esagan on 1/16/2016.
 * @version 1.0
 */
public class ObjectUtils {

    public static <T> List<T> mapObjects(Object[] objects, Class<T> clazz) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        ArrayList<T> ts = new ArrayList();
        for (int i=0; i < objects.length; i++)
        {
            T t = mapObject(objects[i], clazz);
            ts.add(t);
        }
        return ts;
    }

    public static <T> T mapObject(Object obj, Class<T> clazz) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        T t = clazz.newInstance();
        Map project =  (Map) obj;
        BeanUtils.populate(t, project);
        return t;
    }

    public static void dumpObjects(List list){
        for (int i = 0; i < list.size(); i++) {
            dumpObject(list.get(i));
        }
    }

    public static void dumpExternalLinks(Map<Page,List<String>> map){
        for (Map.Entry<Page,List<String>> e : map.entrySet()){
            dumpObject("for page with title: " + e.getKey().getTitle());
            for(String link : e.getValue()){
                dumpObject(link);
            }
        }
    }

    public static void dumpObject(Object o){
        System.out.println(o);
    }
}
