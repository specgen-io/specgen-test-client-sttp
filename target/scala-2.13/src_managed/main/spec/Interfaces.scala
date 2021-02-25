package testservice.client

import scala.concurrent._
import json._

trait IEchoClient {
  import IEchoClient._
  def echoBody(body: Message): Future[EchoBodyResponse]
  def echoQuery(intQuery: Int, stringQuery: String): Future[EchoQueryResponse]
  def echoHeader(intHeader: Int, stringHeader: String): Future[EchoHeaderResponse]
  def echoUrlParams(intUrl: Int, stringUrl: String): Future[EchoUrlParamsResponse]
}

trait ICheckClient {
  import ICheckClient._
  def checkQuery(pString: String, pStringOpt: Option[String], pStringArray: List[String], pDate: java.time.LocalDate, pDateArray: List[java.time.LocalDate], pTime: java.time.LocalTime, pDatetime: java.time.LocalDateTime, pByte: Byte, pInt: Int, pLong: Long, pDecimal: BigDecimal, pChar: Char, pEnum: Choice, pStringDefaulted: String = "the default value"): Future[CheckQueryResponse]
}

object IEchoClient {
  sealed trait EchoBodyResponse { def toResult(): OperationResult }
  object EchoBodyResponse {
    case class Ok(body: Message) extends EchoBodyResponse { def toResult = OperationResult(200, Some(Jsoner.write(body)))}
    def fromResult(result: OperationResult) = result.status match {
      case 200 => Ok(Jsoner.read[Message](result.body.get))
    }
  }
  sealed trait EchoQueryResponse { def toResult(): OperationResult }
  object EchoQueryResponse {
    case class Ok(body: Message) extends EchoQueryResponse { def toResult = OperationResult(200, Some(Jsoner.write(body)))}
    def fromResult(result: OperationResult) = result.status match {
      case 200 => Ok(Jsoner.read[Message](result.body.get))
    }
  }
  sealed trait EchoHeaderResponse { def toResult(): OperationResult }
  object EchoHeaderResponse {
    case class Ok(body: Message) extends EchoHeaderResponse { def toResult = OperationResult(200, Some(Jsoner.write(body)))}
    def fromResult(result: OperationResult) = result.status match {
      case 200 => Ok(Jsoner.read[Message](result.body.get))
    }
  }
  sealed trait EchoUrlParamsResponse { def toResult(): OperationResult }
  object EchoUrlParamsResponse {
    case class Ok(body: Message) extends EchoUrlParamsResponse { def toResult = OperationResult(200, Some(Jsoner.write(body)))}
    def fromResult(result: OperationResult) = result.status match {
      case 200 => Ok(Jsoner.read[Message](result.body.get))
    }
  }
}

object ICheckClient {
  sealed trait CheckQueryResponse { def toResult(): OperationResult }
  object CheckQueryResponse {
    case class Ok() extends CheckQueryResponse { def toResult = OperationResult(200, None)}
    def fromResult(result: OperationResult) = result.status match {
      case 200 => Ok()
    }
  }
}