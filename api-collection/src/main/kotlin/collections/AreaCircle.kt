package collections

import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.pow

fun main() {

    val raio = 60.6
    val pi = 3.14159
    val area = pi * (raio * raio)
    val resultado = BigDecimal(area).setScale(0, RoundingMode.HALF_EVEN)
    println("A= $resultado")

}