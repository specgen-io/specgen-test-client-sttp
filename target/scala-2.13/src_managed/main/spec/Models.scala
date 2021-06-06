package testservice.client

import enumeratum.values._
import java.time._
import java.time.format._
import java.util.UUID
import io.circe.Codec
import io.circe.generic.extras.{Configuration, JsonKey}
import io.circe.generic.extras.semiauto.{deriveConfiguredCodec, deriveUnwrappedCodec}

case class Message(
  @JsonKey("int_field") intField: Int,
  @JsonKey("string_field") stringField: String
)

object Message {
  implicit val config = Configuration.default
  implicit val codec: Codec[Message] = deriveConfiguredCodec
}

sealed abstract class Choice(val value: String) extends StringEnumEntry

case object Choice extends StringEnum[Choice] with StringCirceEnum[Choice] {
  case object FirstChoice extends Choice("FIRST_CHOICE")
  case object SecondChoice extends Choice("SECOND_CHOICE")
  case object ThirdChoice extends Choice("THIRD_CHOICE")
  val values = findValues
}
