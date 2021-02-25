name := "echo-client"

scalaVersion := "2.13.2"
version := "0.0.1"

useJCenter := true

enablePlugins(SpecSttp)

specFile := file("spec.yaml")

libraryDependencies ++= specSttpDependencies ++ Seq(
  "org.scalatest" %% "scalatest" % "3.0.8" % Test,
  "com.softwaremill.sttp" %% "akka-http-backend" % "1.7.1" % Test,
  "com.typesafe.akka" %% "akka-stream" % "2.5.23" % Test,
)

val junitxml = System.getProperty("junitxml", "target/junitxml")
(testOptions in Test) += Tests.Argument(TestFrameworks.ScalaTest, "-u", junitxml)