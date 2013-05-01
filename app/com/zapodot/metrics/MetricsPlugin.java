package com.zapodot.metrics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.Play;
import play.Plugin;

/**
 * @author zapodot at gmail dot com
 */
public class MetricsPlugin extends Plugin{

    private static Logger logger = LoggerFactory.getLogger(MetricsPlugin.class);

    @Override
    public void onStart() {
        logger.info("Starting MetricsPlugin");
        if(MetricsSetup.class.isAssignableFrom(Play.application().getWrappedApplication().global().getClass())) {
            logger.info("MetricsSetup has been enabled. Will time and meter all requests, and count all errors");
        }
    }

    @Override
    public void onStop() {
        logger.info("Stopping MetricsPlugin");
    }
}
