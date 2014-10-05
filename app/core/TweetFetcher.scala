package core

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

import play.api.{Play, Logger}
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
        val maybeTweets = response.json.validate[List[Tweet]].asOpt
        if (maybeTweets.isDefined) {
          Logger.debug("Transform json to tweets with success")

          maybeTweets.get
        } else {
          Logger.warn("Failed to get tweets from json")

          throw new IllegalStateException("Invalid json")
        }
    })
  }

}
