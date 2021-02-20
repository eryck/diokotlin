package collections

fun main(){
    val joao = Funcionario("Joao", 1000.0, "CLT")
    val pedro = Funcionario("Pedro", 2000.0, "CLT")
    val maria = Funcionario("Maria", 5000.0, "PJ")
    val carlos = Funcionario("Carlos", 4000.0, "PJ")
    val augusto = Funcionario("Augusto", 1800.0, "CLT")

    val funcionario1 = setOf(joao, carlos, pedro)
    val funcionario2 = setOf(maria, augusto)

    val resultUnion = funcionario1.union(funcionario2)

    resultUnion.forEach{ println(it)}

    println("**********")

    val funcionario3 = setOf(pedro, maria)
    val resultSubtract = funcionario3.subtract(funcionario2)
    resultSubtract.forEach { println(it) }

    println("**********")
    
    val resultIntercept = funcionario3.intersect(funcionario2)
    resultIntercept.forEach { println(it) }

}