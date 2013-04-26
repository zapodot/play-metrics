package controllers.metrics;

import com.zapodot.metrics.Metrics;
import play.mvc.Result;

import static com.zapodot.metrics.Contents.toJsonContent;
import static play.mvc.Results.ok;

/**
 * Will provide somewhat same functionality for the Play framework as metrics-servlets does for regular Java Web apps
 *
 * @author zapodot at gmail dot com
 */
public class MetricsController {


    public static Result timers() {
        return ok(toJsonContent(Metrics.get().getTimers()));
    }

    public static Result counters() {
        return ok(toJsonContent(Metrics.get().getCounters()));
    }

    public static Result meters() {
        return ok(toJsonContent(Metrics.get().getMeters()));
    }
}
