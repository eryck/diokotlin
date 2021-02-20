package collections

import java.math.BigDecimal

fun main(){

    val salarios = arrayOf("2000".toBigDecimal(),
                            "1500".toBigDecimal(),
                            "4000".toBigDecimal())

    println("************ Somatoria *************")

    println(salarios.somatoria())

    println("*********** Média **************")

    println(salarios.media())
}