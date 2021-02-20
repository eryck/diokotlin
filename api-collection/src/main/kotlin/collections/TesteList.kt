package collections

fun main(){
    val joao = Funcionario("Joao", 1000.0, "CLT")
    val pedro = Funcionario("Pedro", 2000.0, "CLT")
    val maria = Funcionario("Maria", 5000.0, "PJ")
    val carlos = Funcionario("Carlos", 4000.0, "PJ")
    val augusto = Funcionario("Augusto", 1800.0, "CLT")

    val funcionarios = listOf(joao, pedro, maria, carlos, augusto)

    funcionarios.forEach{println(it)}

    println("**********")

    println(funcionarios.find { it.nome == "Maria" })

    println("**********")

    funcionarios.sortedBy { it.salario }.forEach{println(it)}

    println("**********")

    funcionarios.groupBy { it.tipoContratacao }.forEach{println(it)}
}

data class Funcionario(val nome: String, val salario: Double, val tipoContratacao: String){
    override fun toString(): String = """
        Nome: $nome
        Salário: $salario
        Categoria: $tipoContratacao
    """.trimIndent()
}

