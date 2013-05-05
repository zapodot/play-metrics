package org.zapodot.metrics;

import com.codahale.metrics.Timer;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

import static com.codahale.metrics.MetricRegistry.name;

/**
 * @author zapodot at gmail dot com
 */
public class TimerAction extends Action<Timed> {
    @Override
    public Result call(Http.Context ctx) throws Throwable {
        final Timer.Context timer = Metrics.get().timer(name("timer", ctx.request().method(), ctx.request().uri())).time();
        try {
            return delegate.call(ctx);
        } finally {
            timer.stop();
        }
    }
}
