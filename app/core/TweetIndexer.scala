package core

import com.github.cleverage.elasticsearch.ScalaHelpers._

import play.api.libs.json._
import play.api.libs.functional.syntax._

import model.{User, Tweet}

object TweetIndexer extends IndexableManager[Tweet] {
  val indexType = "tweet"

  implicit val userReads = Json.reads[User]
  implicit val userWrites = Json.writes[User]

  implicit val reads =  Json.reads[Tweet]
  implicit val writes = Json.writes[Tweet]

}