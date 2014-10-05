name := """play-twitter-elastic"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  ws,
  ("com.clever-age" % "play2-elasticsearch" % "1.1.0")
    .exclude("com.typesafe.play", "play-functional_2.10")
    .exclude("com.typesafe.akka", "akka-actor_2.10")
    .exclude("com.typesafe.play", "play-json_2.10")
    .exclude("com.typesafe.play", "play_2.10")
    .exclude("com.typesafe.play", "play-iteratees_2.10")
    .exclude("com.typesafe.akka", "akka-slf4j_2.10")
    .exclude("org.scala-stm", "scala-stm_2.10")
    .exclude("com.typesafe.play", "play-datacommons_2.10")
)
