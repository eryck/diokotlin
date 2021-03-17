package collections

import kotlin.math.sqrt

fun main(){

    var n = 4

    for(i in 1..n){
        //var x = (1..8).shuffled().first()
        var x: Double = 0.444
        println(x)
        var raiz = sqrt(x.toDouble()).toInt()
        var test = false
        for (j in 2..raiz) {
            if(x.toInt() % j == 0){
                test = true
                println("Not Prime")
                break
            }
            //println("Prime")
        }
        if(!test)println("Prime")
        //else println("Not Prime")
    }




}

/*
fun main(args: Array<String>) {
    //continue a solução
    val n = readLine()!!.toInt()

    for (i in 1..n) {
        val x = readLine()!!.toDouble()
        val raiz = sqrt(x).toInt()
        var test = false
        for (j in 2..raiz){
            if((x.toInt() % j) == 0){
                test = true
                println("Not Prime")
                break
            }

        }
        if(!test)println("Prime")
        //else println("Not Prime")
    }
}

 */