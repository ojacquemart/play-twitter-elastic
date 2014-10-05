package model

import com.github.cleverage.elasticsearch.ScalaHelpers._
import org.joda.time.DateTime

case class User(id: Long,
                name: String,
                screenName: String,
                profileImageURL: String,
                profileURL: String)

case class Tweet(id: String,
                 text: String,
                 url: String,
                 source: String,
                 createdAt: DateTime,
                 user: User,
                 matches: Boolean,
                 favoriteCount: Int,
                 retweetCount: Int) extends Indexable
