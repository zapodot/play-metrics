package org.zapodot.metrics;

import com.codahale.metrics.Metric;
import com.codahale.metrics.MetricFilter;
import org.junit.Test;

import java.util.concurrent.Callable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * @author zapodot at gmail dot com
 */
public class MetricsTest {

    public static final String DEFAULT_ANSWER = "Answer";
    public static final String OPERATION_NAME = "operation";

    @Test
    public void testTimeCallable() throws Exception {
        final String answer = Metrics.timeCallable(OPERATION_NAME, new Callable<String>() {
            @Override
            public String call() throws Exception {
                return DEFAULT_ANSWER;
            }
        });
        assertEquals(DEFAULT_ANSWER, answer);
        assertNotNull(Metrics.get().getTimers(new MetricFilter() {
            @Override
            public boolean matches(String name, Metric metric) {
                return name.equals(OPERATION_NAME);
            }
        }));

    }

}
