package collections

fun main(){
    val salarios = doubleArrayOf(1000.0, 2000.0, 980.0, 1500.0, 3500.0, 4000.0)

    for (salario in salarios){
        println(salario)
    }

    println("**********")

    println("${salarios.max()}  Maior")
    println("${salarios.min()}  Menor")
    println("${salarios.average()}  Média")

    println("**********")

    val salariosMaior1000  = salarios.filter { it > 999}
    salariosMaior1000.forEach{println(it)}

    println("**********")

    println(salarios.count{it in 2000.0..5000.0})

    println("**********")

    println(salarios.find { it == 2000.0 })

    println("**********")

    println(salarios.find { it == 1555.0 })

    println("**********")

    println(salarios.any { it == 1000.0 })

}