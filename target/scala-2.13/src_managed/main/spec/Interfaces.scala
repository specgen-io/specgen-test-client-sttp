package testservice.client

import scala.concurrent._

trait IEchoClient {
  import IEchoClient._
  def echoBody(body: Message): Future[EchoBodyResponse]
  def echoQuery(intQuery: Int, stringQuery: String): Future[EchoQueryResponse]
  def echoHeader(intHeader: Int, stringHeader: String): Future[EchoHeaderResponse]
  def echoUrlParams(intUrl: Int, stringUrl: String): Future[EchoUrlParamsResponse]
}

trait ICheckClient {
  import ICheckClient._
  def checkQuery(pString: String, pStringOpt: Option[String], pStringArray: List[String], pDate: java.time.LocalDate, pDateArray: List[java.time.LocalDate], pDatetime: java.time.LocalDateTime, pInt: Int, pLong: Long, pDecimal: BigDecimal, pEnum: Choice, pStringDefaulted: String = "the default value"): Future[CheckQueryResponse]
  def checkForbidden(): Future[CheckForbiddenResponse]
}

object IEchoClient {
  sealed trait EchoBodyResponse
  object EchoBodyResponse {
    case class Ok(body: Message) extends EchoBodyResponse
  }
  sealed trait EchoQueryResponse
  object EchoQueryResponse {
    case class Ok(body: Message) extends EchoQueryResponse
  }
  sealed trait EchoHeaderResponse
  object EchoHeaderResponse {
    case class Ok(body: Message) extends EchoHeaderResponse
  }
  sealed trait EchoUrlParamsResponse
  object EchoUrlParamsResponse {
    case class Ok(body: Message) extends EchoUrlParamsResponse
  }
}

object ICheckClient {
  sealed trait CheckQueryResponse
  object CheckQueryResponse {
    case class Ok() extends CheckQueryResponse
  }
  sealed trait CheckForbiddenResponse
  object CheckForbiddenResponse {
    case class Forbidden() extends CheckForbiddenResponse
  }
}
