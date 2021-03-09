package testservice.client

import enumeratum.values._
import java.time._
import java.time.format._
import java.util.UUID
import io.circe.generic.extras.{AutoDerivation, Configuration}
import io.circe.{Decoder, Encoder}
import io.circe.generic.extras.semiauto.{deriveUnwrappedDecoder, deriveUnwrappedEncoder}

case class Message(
  intField: Int,
  stringField: String
)

case class Parent(
  field: String,
  nested: Nested
)

case class Nested(
  field: String
)

sealed abstract class Choice(val value: String) extends StringEnumEntry

case object Choice extends StringEnum[Choice] with StringCirceEnum[Choice] {
  case object FirstChoice extends Choice("FIRST_CHOICE")
  case object SecondChoice extends Choice("SECOND_CHOICE")
  case object ThirdChoice extends Choice("THIRD_CHOICE")
  val values = findValues
}

case class EnumFields(
  enumField: Choice
)

case class NumericFields(
  intField: Int,
  longField: Long,
  floatField: Float,
  doubleField: Double,
  decimalField: BigDecimal
)

case class NonNumericFields(
  booleanField: Boolean,
  stringField: String,
  uuidField: java.util.UUID,
  dateField: java.time.LocalDate,
  datetimeField: java.time.LocalDateTime
)

case class ArrayFields(
  intArrayField: List[Int],
  stringArrayField: List[String]
)

case class MapFields(
  intMapField: Map[String, Int],
  stringMapField: Map[String, String]
)

case class OptionalFields(
  intOptionField: Option[Int],
  stringOptionField: Option[String]
)

case class RawJsonField(
  jsonField: io.circe.Json
)

case class OrderCreated(
  id: java.util.UUID,
  sku: String,
  quantity: Int
)

case class OrderChanged(
  id: java.util.UUID,
  quantity: Int
)

case class OrderCanceled(
  id: java.util.UUID
)

sealed trait OrderEvent

object OrderEvent {
  case class Created(data: testservice.client.OrderCreated) extends OrderEvent
  case class Changed(data: testservice.client.OrderChanged) extends OrderEvent
  case class Canceled(data: testservice.client.OrderCanceled) extends OrderEvent
}

object json extends AutoDerivation {
  implicit val auto = Configuration.default.withSnakeCaseMemberNames.withSnakeCaseConstructorNames.withDefaults
  implicit val encoderOrderEventCreated: Encoder[OrderEvent.Created] = deriveUnwrappedEncoder
  implicit val decoderOrderEventCreated: Decoder[OrderEvent.Created] = deriveUnwrappedDecoder
  implicit val encoderOrderEventChanged: Encoder[OrderEvent.Changed] = deriveUnwrappedEncoder
  implicit val decoderOrderEventChanged: Decoder[OrderEvent.Changed] = deriveUnwrappedDecoder
  implicit val encoderOrderEventCanceled: Encoder[OrderEvent.Canceled] = deriveUnwrappedEncoder
  implicit val decoderOrderEventCanceled: Decoder[OrderEvent.Canceled] = deriveUnwrappedDecoder
}
