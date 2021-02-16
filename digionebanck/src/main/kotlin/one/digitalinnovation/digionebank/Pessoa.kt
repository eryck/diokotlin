package one.digitalinnovation.digionebank


class Pessoa {
    var nome: String = "Eryck"
    var cpf: String = "123.456.789-25"
}

fun main(){
    val eryck = Pessoa()
    println(eryck.nome)
    println(eryck.cpf)

}