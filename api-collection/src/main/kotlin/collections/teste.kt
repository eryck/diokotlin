package collections
fun main() {

    val r = 3002.00F
    var i = 0F

    if (r <= 2000.00F) {
        i= 0F
    }else if (r >= 2000.01F || r <= 3000.00F){
       var x = r - 2000.00F
        i = x * 0.8F
    }else if (r >= 3000.01F || r <= 4500.00F){
        var x = r - 2000.00F
        i = x * 0.18F
    }

    if (i == 0F) println("Insento") else print("R$ $i")
}