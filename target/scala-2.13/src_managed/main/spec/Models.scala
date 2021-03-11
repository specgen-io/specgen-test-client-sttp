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

sealed abstract class Choice(val value: String) extends StringEnumEntry

case object Choice extends StringEnum[Choice] with StringCirceEnum[Choice] {
  case object FirstChoice extends Choice("FIRST_CHOICE")
  case object SecondChoice extends Choice("SECOND_CHOICE")
  case object ThirdChoice extends Choice("THIRD_CHOICE")
  val values = findValues
}

object json extends AutoDerivation {
  implicit val auto = Configuration.default.withSnakeCaseMemberNames.withSnakeCaseConstructorNames.withDefaults
}
