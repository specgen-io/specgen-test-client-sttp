package testservice.client

import enumeratum.values._
import java.time._
import java.time.format._
import java.util.UUID
import io.circe.{Decoder, Encoder}
import io.circe.generic.extras.Configuration
import io.circe.generic.extras.semiauto.{deriveConfiguredDecoder, deriveConfiguredEncoder, deriveUnwrappedDecoder, deriveUnwrappedEncoder}

case class Message(
  intField: Int,
  stringField: String
)

sealed abstract class Choice(val value: String) extends StringEnumEntry

case object Choice extends StringEnum[Choice] with StringCirceEnum[Choice] {
  case object FirstChoice extends Choice("FIRST_CHOICE")
  case object SecondChoice extends Choice("SECOND_CHOICE")
  case object ThirdChoice extends Choice("THIRD_CHOICE")
  val values = findValues
}

object json {
  implicit val auto = Configuration.default.withSnakeCaseMemberNames.withSnakeCaseConstructorNames.withDefaults
  implicit val encoderMessage: Encoder[Message] = deriveConfiguredEncoder
  implicit val decoderMessage: Decoder[Message] = deriveConfiguredDecoder
}
