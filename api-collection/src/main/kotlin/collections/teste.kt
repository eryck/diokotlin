package collections
fun main() {

    val r = 4520.00F
    var i = 0F

    if (r <= 2000.00F) {
        i= 0F
    }else if (r <= 3000.00F){
       var x = r - 2000.00F
        i = x * 0.08F
    }else if (r <= 4500.00F){
        var x = r - 3000.00F
        i = (x * 0.18F) + 80.00F
    }else if (r >= 4500.00F){
        var x = r - 4500.00F
        i = (x * 0.28F) + 350.00F
    }

    if (i == 0F) println("Insento") else print("R$ $i")
}