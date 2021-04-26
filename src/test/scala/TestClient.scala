package testservice.client

import org.scalatest.FlatSpec

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import com.softwaremill.sttp.akkahttp.AkkaHttpBackend
import testservice.client.tests.Util

class EchoClientSpec extends FlatSpec {
  implicit val httpBackend = AkkaHttpBackend()
  import testservice.client.IEchoClient._

  "echoBody" should "return body with same members values" in {
    val client = new EchoClient(Util.service_url)
    val body = Message(intField = 123, stringField = "some string")
    val responseFuture = client.echoBody(body)
    val actual = Await.ready(responseFuture, Duration.Inf).value.get.get
    val expected = EchoBodyResponse.Ok(body)
    assert(actual == expected)
  }

  "echoQuery" should "return query params with same values" in {
    val client = new EchoClient(Util.service_url)
    val responseFuture = client.echoQuery(123, "some string")
    val actual = Await.ready(responseFuture, Duration.Inf).value.get.get
    val expected = EchoQueryResponse.Ok(Message(intField = 123, stringField = "some string"))
    assert(actual == expected)
  }

  "echoHeader" should "return header params" in {
    val client = new EchoClient(Util.service_url)
    val responseFuture = client.echoHeader(123, "some string")
    val actual = Await.ready(responseFuture, Duration.Inf).value.get.get
    val expected = EchoHeaderResponse.Ok(Message(intField = 123, stringField = "some string"))
    assert(actual == expected)
  }

  "echoUrlParams" should "return url params" in {
    val client = new EchoClient(Util.service_url)
    val responseFuture = client.echoUrlParams(123, "value")
    val actual = Await.ready(responseFuture, Duration.Inf).value.get.get
    val expected = EchoUrlParamsResponse.Ok(Message(intField = 123, stringField = "value"))
    assert(actual == expected)
  }
}

class CheckClientSpec extends FlatSpec {
  implicit val httpBackend = AkkaHttpBackend()
  import testservice.client.ICheckClient._

  "echoBody" should "return body with same members values" in {
    val client = new CheckClient(Util.service_url)
    val responseFuture = client.checkQuery(
      "the string",
      Some("the string"),
      List("string 1", "string 2"),
      java.time.LocalDate.parse("2021-01-01"),
      List(java.time.LocalDate.parse("2021-01-02")),
      java.time.LocalDateTime.parse("2021-01-02T23:54"),
      123,
      123.toLong,
      BigDecimal("123"),
      pEnum=Choice.SecondChoice,
    )
    val actual = Await.ready(responseFuture, Duration.Inf).value.get.get
    val expected = CheckQueryResponse.Ok()
    assert(actual == expected)
  }
}