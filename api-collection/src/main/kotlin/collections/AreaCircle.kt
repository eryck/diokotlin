package collections

import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.pow

fun main() {

    val raio = 60.6
    val pi = 3.14159
    val area = pi * (raio.pow(2))
    val resultado = BigDecimal(area).setScale(4, RoundingMode.HALF_UP)
    println("A= $resultado")

}