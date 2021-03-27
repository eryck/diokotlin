fun main(args: Array<String>) {

    val N = readLine()!!.toInt()

    for (i in 1..N) {

        var leitor: List<String> = readLine()!!.split(" ")

        for(i in leitor){

            var N1 = i.substring(2,3).toInt()
            var N2 = i.substring(0,1).toInt()


            if (N1 == N2) {
                println(N1 * N2)
            } else {
                if(i.contentEquals(i.toUpperCase()) && N2 != N1){
                    println(N1 - N2)
                }else{
                    println(N1 + N2)
                }
            }

        }

    }
}

/* Código Original do teste
fun main(args: Array<String>) {

    val N = readLine()!!.toInt()
    for (i in 1..N) {

        //escreva sua solução aqui
        else println(N1 + N2)

    }
}
 */

