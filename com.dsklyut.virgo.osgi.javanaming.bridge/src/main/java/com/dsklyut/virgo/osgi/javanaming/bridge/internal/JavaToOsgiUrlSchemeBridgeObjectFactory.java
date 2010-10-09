package com.dsklyut.virgo.osgi.javanaming.bridge.internal;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.jndi.JNDIConstants;
import org.osgi.service.jndi.JNDIContextManager;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.spi.ObjectFactory;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * User: dsklyut
 * Date: Oct 8, 2010
 * Time: 12:42:59 PM
 */
public class JavaToOsgiUrlSchemeBridgeObjectFactory implements ObjectFactory {

    private final BundleContext clientBundleContext;

    public JavaToOsgiUrlSchemeBridgeObjectFactory(BundleContext clientBundleContext) {
        this.clientBundleContext = clientBundleContext;
    }

    @Override
    public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment) throws Exception {
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),
                                      new Class[]{Context.class},
                                      new WrapperHandler(clientBundleContext));
    }


    private final static class WrapperHandler implements InvocationHandler {

        private final BundleContext context;

        public WrapperHandler(BundleContext context) {
            this.context = context;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            if (method.getName().equals("lookup") && method.getParameterTypes()[0].equals(java.lang.String.class)) {
                String argument = (String) args[0];
                argument = "osgi:service/" + argument.substring(argument.lastIndexOf("/") + 1);

                ServiceReference ref = context.getServiceReference(JNDIContextManager.class.getName());
                if (ref != null) {
                    JNDIContextManager manager = (JNDIContextManager) context.getService(ref);
                    Map env = new HashMap();
                    env.put(JNDIConstants.BUNDLE_CONTEXT, context);
                    Context c = manager.newInitialContext(env);
                    return c.lookup(argument);
                }
            }
            return new RuntimeException("This operation is not supported by JavaToOsgi Namespace Context");
        }
    }
}
