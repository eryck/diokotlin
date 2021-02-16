package one.digitalinnovation.digionebank


class Pessoa {
    var nome: String = "Eryck"
    var cpf: String = "123.456.789-25"

    constructor()
    fun pessoaInfo() = "$nome e $cpf"
}

fun main(){
    val eryck = Pessoa()

    println(eryck.pessoaInfo())
}