package com.dsklyut.virgo.osgi.javanaming.bridge.internal;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * User: dsklyut
 * Date: Oct 8, 2010
 * Time: 1:51:02 PM
 */
public class TestParse {


    @Test
    public void testUrlParse() {
        String url = "java:comp/env/resource";
        String parsed = "osgi:service/" + url.substring(url.lastIndexOf("/") + 1);

        assertEquals("osgi:service/resource", parsed);

    }
}
