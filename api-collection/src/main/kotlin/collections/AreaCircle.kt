package collections

import java.math.BigDecimal
import java.math.RoundingMode

fun main() {

    val raio = 2
    val pi = 3.14
    val area = 3
    val resultado = BigDecimal(area).setScale(0, RoundingMode.HALF_EVEN)
    println("A= $resultado")

}