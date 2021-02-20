package collections

fun main(){
    val pair: Pair<String, Double> = Pair("Joao", 1000.0)
    val map1 = mapOf(pair)

    map1.forEach { (t, u) ->
        println("Chave: $t = Valor $u")
    }

    val map2 = mapOf("Pedtro" to 2500.0, "Maria" to 3000.0)
    map2.forEach { t, u -> println("Chave: $t = Valor $u") }


}