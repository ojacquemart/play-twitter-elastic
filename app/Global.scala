import scala.concurrent.duration._

import play.api._
import play.api.Play.current
import play.api.libs.concurrent._
import play.api.libs.concurrent.Execution.Implicits._

import core.TweetTask

object Global extends GlobalSettings {

  val defaultDelayInMinutes = 5

  override def onStart(app: Application) {
    Logger.info("Application has started...")

    startScheduledTask
  }

  override def onStop(app: Application) {
    Logger.info("Application shutdown...")
  }

  def startScheduledTask = {
    Logger.info("Starting scheduled task")

    val delay = getDelay()
    Akka.system.scheduler.schedule(0 seconds, delay minutes) {
      TweetTask.fetchAndIndex
    }
  }

  def getDelay() = {
    val delayInMinutes = Play.application.configuration
      .getInt("twitter.fetch.delay")
      .getOrElse(defaultDelayInMinutes)

    Logger.info("Fetch delay is " + delayInMinutes + "m")

    delayInMinutes
  }

}