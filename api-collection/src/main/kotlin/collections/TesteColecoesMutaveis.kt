package collections

fun main(){
    val joao = Funcionario("Joao", 1000.0, "CLT")
    val pedro = Funcionario("Pedro", 2000.0, "CLT")
    val maria = Funcionario("Maria", 5000.0, "PJ")
    val carlos = Funcionario("Carlos", 4000.0, "PJ")
    val augusto = Funcionario("Augusto", 1800.0, "CLT")

    println("*********** LIST **************")

    val funcionarios = mutableListOf(joao, pedro, maria)
    funcionarios.forEach{ println(it)}

    println("*************************")

    funcionarios.add(carlos)
    funcionarios.forEach{ println(it)}

    println("*************************")

    funcionarios.remove(joao)
    funcionarios.forEach{ println(it)}

    println()
    println("*********** SET **************")

    val funcionarioSet = mutableSetOf(joao)
    funcionarioSet.forEach{println(it)}

    println("*************************")

    funcionarioSet.add(maria)
    funcionarioSet.add(pedro)
    funcionarioSet.forEach{println(it)}

    println("*************************")

    funcionarioSet.remove(pedro)
    funcionarioSet.forEach{println(it)}
}