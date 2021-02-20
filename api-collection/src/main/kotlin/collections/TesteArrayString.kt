package collections

fun main(){

    val nomes = Array<String>(3){""}
    nomes[0] = "Eryck"
    nomes[1] = "Bruna"
    nomes[2] = "John"

    for (nome in nomes){
        println(nome)
    }

    println("*****************")

    nomes.sort()
    nomes.forEach { println(it) }

    println("*****************")

    val nomes2 = arrayOf("Maria", "Zaza", "Pedro")
    nomes2.sort()
    nomes2.forEach { println(it) }

}