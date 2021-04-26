package testservice.client.v2

import org.scalatest.FlatSpec

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import com.softwaremill.sttp.akkahttp.AkkaHttpBackend
import testservice.client.tests.Util
import testservice.client.v2.{EchoClient, Message}

class EchoClientSpec extends FlatSpec {
  implicit val httpBackend = AkkaHttpBackend()
  import testservice.client.v2.IEchoClient._

  "echoBody" should "return body with same members values" in {
    val client = new EchoClient(Util.service_url)
    val body = Message(boolField = true, stringField = "some string")
    val responseFuture = client.echoBody(body)
    val actual = Await.ready(responseFuture, Duration.Inf).value.get.get
    val expected = EchoBodyResponse.Ok(body)
    assert(actual == expected)
  }
}