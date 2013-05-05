import play.api.{Logger, Plugin, Application}

package org.zapodot.metrics {

import play.api.mvc.{WithFilters, Result, RequestHeader, Filter}
import com.codahale.metrics.Timer.Context
import play.api.GlobalSettings

/**
 * Plugin class that plugs Metrics into a Play Framework application.
 * @param app
 */
class MetricsPlugin(app: Application) extends Plugin {

  val logger = Logger

  override def onStart() {
    logger.info("Starting MetricsPlugin")
    if(app.global == MetricsSetup) {
      logger.info("The MetricsSetup Global setting has been enabled. All requests will be timed and all controller errors counted")
    }
  }
}

/**
 * Global object that enables error counter and Timer
 */
object MetricsSetup extends WithFilters(TimerFilter) with GlobalSettings {
  override def onError(request: RequestHeader, ex: Throwable): Result = {
    Metrics.countThrowable(request, ex);
    super.onError(request, ex)
  }
}

/**
 * Filter to be used for timing requests
 */
object TimerFilter extends Filter {
  def apply(next: (RequestHeader) => Result)(requestHeader: RequestHeader): Result = {
    val timeContext: Context = Metrics.timerForRequest(requestHeader).time()
    val result = next(requestHeader)
    timeContext.stop();
    result
  }
}



}