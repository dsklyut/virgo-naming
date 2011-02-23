package com.dsklyut.virgo.osgi.javanaming.bridge.internal;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.jndi.JNDIConstants;

import javax.naming.spi.ObjectFactory;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

public final class Activator implements BundleActivator {

    private final static String JAVA_URL_SCHEME = "java";

    private final List<ServiceRegistration> serviceRegistrations = new LinkedList<ServiceRegistration>();


    @SuppressWarnings({"unchecked"})
    @Override
    public void start(BundleContext context) throws Exception {

        // register ObjectFactory
        final Hashtable serviceProperties = new Hashtable();
        serviceProperties.put(JNDIConstants.JNDI_URLSCHEME, JAVA_URL_SCHEME);
        serviceRegistrations.add(context.registerService(ObjectFactory.class.getName(),
                                                         new JavaToOsgiUrlSchemeBridgeObjectFactoryFactory(),
                                                         serviceProperties));


    }

    @Override
    public void stop(BundleContext context) throws Exception {
        for (ServiceRegistration s : serviceRegistrations) {
            if (s != null) {
                s.unregister();
            }
        }
    }

}
