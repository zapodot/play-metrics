package org.zapodot.metrics;

import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;
import static com.codahale.metrics.MetricRegistry.name;

/**
 * @author zapodot at gmail dot com
 */
public class MeterAction extends Action<Metered> {

    @Override
    public Result call(Http.Context ctx) throws Throwable {

        final Http.Request request = ctx.request();
        Metrics.get().meter(name("meter", request.method(), request.uri())).mark();
        return delegate.call(ctx);
    }

}
