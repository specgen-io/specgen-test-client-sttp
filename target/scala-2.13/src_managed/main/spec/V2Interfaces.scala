package testservice.client.v2

import scala.concurrent._
import spec.Jsoner
import spec.OperationResult
import json._

trait IEchoClient {
  import IEchoClient._
  def echoBody(body: Message): Future[EchoBodyResponse]
}

object IEchoClient {
  sealed trait EchoBodyResponse { def toResult(): OperationResult }
  object EchoBodyResponse {
    case class Ok(body: Message) extends EchoBodyResponse { def toResult = OperationResult(200, Some(Jsoner.write(body)))}
    def fromResult(result: OperationResult) = result.status match {
      case 200 => Ok(Jsoner.read[Message](result.body.get))
    }
  }
}
