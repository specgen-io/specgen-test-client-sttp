package testservice.client.v2

import enumeratum.values._
import java.time._
import java.time.format._
import java.util.UUID
import io.circe.generic.extras.{AutoDerivation, Configuration}
import io.circe.{Decoder, Encoder}
import io.circe.generic.extras.semiauto.{deriveUnwrappedDecoder, deriveUnwrappedEncoder}

case class Message(
  boolField: Boolean,
  stringField: String
)

object json extends AutoDerivation {
  implicit val auto = Configuration.default.withSnakeCaseMemberNames.withSnakeCaseConstructorNames.withDefaults
}
