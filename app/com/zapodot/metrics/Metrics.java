package com.zapodot.metrics;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;

import java.util.concurrent.Callable;

/**
 * @author zapodot at gmail dot com
 */
public class Metrics {
    private static MetricRegistry metricRegistry;

    static {
        metricRegistry = new MetricRegistry();
    }

    public static MetricRegistry get() {
        return metricRegistry;
    }

    public static <T> T timeCallable(String operationName, Callable<T> callable) {
        final Timer.Context timerContext = get().timer(operationName).time();
        try {
            return callable.call();
        } catch (Exception e) {
            throw new RuntimeException("An exception occurred while calling callable", e);
        } finally {
            timerContext.stop();
        }

    }

}
