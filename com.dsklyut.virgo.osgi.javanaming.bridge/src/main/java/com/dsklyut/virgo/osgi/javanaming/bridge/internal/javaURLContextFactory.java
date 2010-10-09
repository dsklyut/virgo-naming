package com.dsklyut.virgo.osgi.javanaming.bridge.internal;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.spi.InitialContextFactory;
import javax.naming.spi.ObjectFactory;
import java.util.Hashtable;

/**
 *
 * This is a bridge between "java:/" prefix and "osgi:service/"
 * 
 * User: dsklyut
 * Date: Oct 7, 2010
 * Time: 12:30:27 PM
 */
final class javaURLContextFactory implements InitialContextFactory, ObjectFactory {

    @Override
    public Context getInitialContext(Hashtable<?, ?> environment) throws NamingException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment) throws Exception {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
