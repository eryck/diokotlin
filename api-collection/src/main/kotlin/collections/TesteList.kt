package collections

fun main(){
    val joao = Funcionario("Joao", 1000.0)
    val pedro = Funcionario("Pedro", 2000.0)
    val maria = Funcionario("Maria", 4000.0)
    val carlos = Funcionario("Carlos", 3000.0)
    val augusto = Funcionario("Augusto", 1800.0)

    val funcionarios = listOf(joao, pedro, maria, carlos, augusto)

    funcionarios.forEach{println(it)}

    println("**********")

    println(funcionarios.find { it.nome == "Maria" })

    println("**********")

    funcionarios.sortedBy { it.salario }.forEach{println(it)}
}

data class Funcionario(val nome: String, val salario: Double){
    override fun toString(): String = """
        Nome: $nome
        Salário: $salario
    """.trimIndent()
}

