
fun main(args: Array<String>) {

    var s: String
    var n: Int
    val r = """0[a-zA-Z].*""".toRegex()
    val t = """[a-zA-Z].*""".toRegex()
    var valida = true
    val contador = mutableListOf<String>()

    while (valida) {

        s = readLine()!!.toString()

        if(s == "-1") valida = false

        if(!s.contains(r) && s.toInt() < Integer.MAX_VALUE && s.toInt() > 0){
            n = s.toInt()
            val hexadecimal = Integer.toHexString(n).toUpperCase()
            contador.add("0x$hexadecimal")
        }
        if(s.contains(r) && s.contains(t)){
            val formata = s.removeRange(0,2)
            n = formata.toInt(16)
            contador.add(n.toString())
        }
    }

    for(i in contador){
        println(i)
    }
}

/* Código original do teste
fun main(args: Array<String>) {

var s: String
var n: Int
val r = """0x.*""".toRegex()
while (true) {

//escreva sua solução aqui

}
}
}
 */