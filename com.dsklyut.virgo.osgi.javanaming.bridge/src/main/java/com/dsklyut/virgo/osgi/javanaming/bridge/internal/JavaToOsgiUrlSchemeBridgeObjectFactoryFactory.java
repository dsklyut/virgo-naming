package com.dsklyut.virgo.osgi.javanaming.bridge.internal;

import org.osgi.framework.Bundle;
import org.osgi.framework.ServiceFactory;
import org.osgi.framework.ServiceRegistration;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.Name;
import javax.naming.spi.ObjectFactory;
import java.util.Hashtable;

/**
 * User: dsklyut
 * Date: Oct 7, 2010
 * Time: 5:00:06 PM
 */
public class JavaToOsgiUrlSchemeBridgeObjectFactoryFactory implements ServiceFactory {

    @Override
    public Object getService(Bundle bundle, ServiceRegistration registration) {
        return new JavaToOsgiUrlSchemeBridgeObjectFactory(bundle.getBundleContext());
    }

    @Override
    public void ungetService(Bundle bundle, ServiceRegistration registration, Object service) {

    }


}
