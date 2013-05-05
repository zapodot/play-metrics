package org.zapodot.metrics;

import com.codahale.metrics.Counter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import play.api.mvc.RequestHeader;

import java.util.concurrent.Callable;

import static com.codahale.metrics.MetricRegistry.name;

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

    public static Timer timerForRequest(RequestHeader requestHeader) {
        return get().timer(name("request-timer", requestHeader.uri(), requestHeader.method()));
    }

    public static Counter countThrowable(RequestHeader requestHeader, Throwable t) {
        return get().counter(name("error", t.getClass().getName(), requestHeader.uri(), requestHeader.method()));
    }

}
