package com.zapodot.metrics;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import com.google.common.base.Joiner;
import play.GlobalSettings;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

import java.lang.reflect.Method;

import static com.codahale.metrics.MetricRegistry.name;

/**
 * @author zapodot at gmail dot com
 */
public class MetricsGlobal extends GlobalSettings {

    @Override
    public Action onRequest(Http.Request request, Method actionMethod) {
        final MetricRegistry metrics = Metrics.get();
        final Timer.Context timer = metrics.timer(name(request.uri(), request.method())).time();
        try {
            return super.onRequest(request, actionMethod);
        } finally {
            metrics.meter(name(actionMethod.getDeclaringClass(), actionMethod.getName(), createParameterDescription(actionMethod)));
            timer.stop();
        }
    }

    @Override
    public Result onError(Http.RequestHeader request, Throwable t) {
        Metrics.get().counter(name("error", t.getClass().getName()));
        return super.onError(request, t);
    }

    private String createParameterDescription(final Method actionMethod) {
        return new StringBuilder("(")
                .append(Joiner.on(",").join(actionMethod.getParameterTypes()))
                .append(")")
                .toString();
    }
}
