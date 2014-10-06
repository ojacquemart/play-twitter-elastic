package core

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

import play.api.{Play, Logger}
import play.api.libs.json.JsResultException
import play.api.libs.ws.WS
import play.api.Play.current

import model.Tweet
import TweetIndexer._

object TweetFetcher {

  def fetch(): Future[List[Tweet]] = {
    Logger.info("Fetching tweets")

    WS.url(Play.application.configuration.getString("twitter.search.url").get)
      .get()
      .map(response => {
        Logger.debug("Reading json from tweets")

        try {
          response.json.as[Seq[Tweet]].toList
        }
        catch {
          case e: JsResultException => {
            Logger.warn("Error while reading json", e)
            throw new IllegalStateException("Invalid json", e)
          }
        }
    })
  }

}
