package com.dsklyut.virgo.osgi.javanaming.cleanup.internal;

import java.util.Properties;

import javax.naming.Context;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Activator implements BundleActivator {

    private final Logger logger = LoggerFactory.getLogger(Activator.class);

    private Properties saved = new Properties();

    @Override
    public void start(BundleContext context) throws Exception {
        cleaup(saved, System.getProperties());
        // try {
        // InitialContext ctx = new InitialContext();
        // logger.error("InitialContext = " + ctx);
        // logger.error("InitialContext class = " + ctx.getClass());
        // Object lookup = ctx.lookup("osgi:/");
        // logger.error("Lookup = " + lookup);
        // ctx.close();
        // } finally {
        //
        // }

    }

    private void restore(Properties saved, Properties properties) {
        properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, saved.getProperty(Context.INITIAL_CONTEXT_FACTORY));
        properties.setProperty(Context.URL_PKG_PREFIXES, saved.getProperty(Context.URL_PKG_PREFIXES));

    }

    private void cleaup(Properties saved, Properties properties) {
        saved.setProperty(Context.INITIAL_CONTEXT_FACTORY, properties.getProperty(Context.INITIAL_CONTEXT_FACTORY));
        saved.setProperty(Context.URL_PKG_PREFIXES, properties.getProperty(Context.URL_PKG_PREFIXES));

        properties.remove(Context.INITIAL_CONTEXT_FACTORY);
        properties.remove(Context.URL_PKG_PREFIXES);
        logger.warn("Removed {} system properties", saved);

    }

    @Override
    public void stop(BundleContext context) throws Exception {
        restore(saved, System.getProperties());
        logger.warn("Restored {} system properties", saved);
    }

}
