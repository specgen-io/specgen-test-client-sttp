package testservice.client.v2

import enumeratum.values._
import java.time._
import java.time.format._
import java.util.UUID
import io.circe.{Decoder, Encoder}
import io.circe.generic.extras.Configuration
import io.circe.generic.extras.semiauto.{deriveConfiguredDecoder, deriveConfiguredEncoder, deriveUnwrappedDecoder, deriveUnwrappedEncoder}

case class Message(
  boolField: Boolean,
  stringField: String
)

object json {
  implicit val auto = Configuration.default.withSnakeCaseMemberNames.withSnakeCaseConstructorNames.withDefaults
  implicit val encoderMessage: Encoder[Message] = deriveConfiguredEncoder
  implicit val decoderMessage: Decoder[Message] = deriveConfiguredDecoder
}
