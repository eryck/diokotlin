package collections

fun main(){

    var n = 1
    var test = false

    for(i in 1..n){
        var x = 5
        for (j in 2..x / 2){
            if(x % j == 0){
                test = true
            }
            false

        }
        if(!test){
            println("Prime")
        }else println("Not Prime")
    }
}