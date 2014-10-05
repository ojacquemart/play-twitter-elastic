package core

import org.joda.time.DateTime

import scala.concurrent.ExecutionContext.Implicits.global

import play.api.Logger

object TweetTask {

  def fetchAndIndex = {
    Logger.info(s"Running twitter task at ${DateTime.now().toString("YYYY-MM-dd hh:mm")}")

    TweetFetcher
      .fetch()
      .map(tweets => {
        if (!tweets.isEmpty) {
          Logger.info(s"Indexing ${tweets.size} tweets")
          TweetIndexer.index(tweets)
        } else {
          Logger.warn("No tweets to index")
        }
    })
  }

}
