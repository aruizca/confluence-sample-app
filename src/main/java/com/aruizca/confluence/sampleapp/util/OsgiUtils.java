package com.aruizca.confluence.sampleapp.util;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/*
 * Copyright (c) 2022 Appfire Technologies, LLC.
 * All rights reserved.
 *
 * This software is licensed under the provisions of the "Appfire EULA"
 * (https://appfire.com/eula/) as well as under the provisions of
 * the "Standard EULA" from the "Atlassian Marketplace Terms of Use" as a "Marketplace Product"
 * (http://www.atlassian.com/licensing/marketplace/termsofuse).
 *
 * See the LICENSE file for more details.
 */

/**
 * Utils and some sugar for osgi tools
 */
public class OsgiUtils {

    private static BundleContext getBundleContext() {
        return FrameworkUtil.getBundle(OsgiUtils.class).getBundleContext();
    }

    public static <T> T getService(Class<T> clazz) {
        return getService(clazz.getName(), clazz);
    }

    public static Object getService(String serviceName) {
        BundleContext bundleContext = getBundleContext();
        return bundleContext.getService(bundleContext.getServiceReference(serviceName));
    }

    public static <T> T getService(String serviceName, Class<T> clazz) {
        BundleContext bundleContext = getBundleContext();
        return clazz.cast(bundleContext.getService(bundleContext.getServiceReference(serviceName)));
    }

    public static ServiceTracker getServiceTracker(Class clazz) {
        return getServiceTracker(clazz.getName());
    }

    public static ServiceTracker getServiceTracker(String serviceName) {
        return new ServiceTracker(getBundleContext(), serviceName, null);
    }
}
