package collections

fun main(){
    val salarios = doubleArrayOf(1000.0, 2000.0, 980.0)

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
}