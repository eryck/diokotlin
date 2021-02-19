package collections

fun main(){
    val values = IntArray(5)

    values[0] = 1
    values[1] = 7
    values[2] = 6
    values[3] = 3
    values[4] = 2

    for(valor in values){
       println(valor)
    }
    println("______________------------")
    values.forEach { valor ->
        println(valor)
    }
    println("______________------------")
    for (index in values.indices){
        println(values[index])
    }
    println("______________------------")
    values.sort()
    for (valor in values){
        println(valor)
    }

}