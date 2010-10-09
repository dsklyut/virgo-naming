package com.dsklyut.virgo.osgi.javanaming.bridge.internal;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.spi.InitialContextFactory;
import javax.naming.spi.InitialContextFactoryBuilder;


final class JavaNamingInitialContextFactoryBuilder implements InitialContextFactoryBuilder {

    private final static String CONTEXT_FACTORY_NAME = javaURLContextFactory.class.getName().intern();

    @Override
    public InitialContextFactory createInitialContextFactory(Hashtable<?, ?> environment) throws NamingException {
        if (CONTEXT_FACTORY_NAME.equals(environment.get(Context.INITIAL_CONTEXT_FACTORY))) {
            return new javaURLContextFactory();
        }
        return null;
    }

}
