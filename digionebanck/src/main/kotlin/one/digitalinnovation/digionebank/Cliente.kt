package one.digitalinnovation.digionebank

class Cliente(nome: String, cpf: String, val clienteTipo: ClienteTipo, val senha: String) : Pessoa(nome, cpf), Logavel {

    override fun login(): Boolean = "12345" == senha

    override fun toString(): String = """
        Nome: $nome
        CPF: $cpf
        Tipo: ${clienteTipo.descricao}
    """.trimIndent()
}