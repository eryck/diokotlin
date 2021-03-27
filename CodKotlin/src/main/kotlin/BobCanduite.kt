import java.util.Scanner

fun main() {

    val qtd = readLine()!!.toInt()
    val contador = mutableListOf<Int>()

    for ( i in 1..qtd) {
        var entrada = readLine()!!.split(" ").map { it.toInt() }
        if(entrada.size == 2){
            var soma = entrada[0] + entrada[1]
            contador.add(soma)
        }
    }
    for (i in contador){
        println(i)
    }
}