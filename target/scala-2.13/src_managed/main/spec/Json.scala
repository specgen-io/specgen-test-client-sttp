package spec

object Jsoner {

  import io.circe._
  import io.circe.syntax._
  import io.circe.parser._

  def readThrowing[T](jsonStr: String)(implicit decoder: Decoder[T]): T = {
    parse(jsonStr) match {
      case Right(parsed) => {
        parsed.as[T] match {
          case Right(decoded) => decoded
          case Left(failure) => throw failure
        }
      }
      case Left(failure) => throw failure
    }
  }

  def read[T](jsonStr: String)(implicit decoder: Decoder[T]): Either[Error, T] =
    parse(jsonStr).flatMap(json => json.as[T])

  def write[T](value: T, formatted: Boolean = false)(implicit encoder: Encoder[T]): String = {
    if (formatted) {
      Printer.spaces2.copy(dropNullValues = true).print(value.asJson)
    } else {
      Printer.noSpaces.copy(dropNullValues = true).print(value.asJson)
    }
  }
}