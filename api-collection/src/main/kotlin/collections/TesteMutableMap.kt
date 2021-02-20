package collections

fun main(){
    val joao = Funcionario("Joao", 1000.0, "CLT")
    val pedro = Funcionario("Pedro", 2000.0, "CLT")
    val maria = Funcionario("Maria", 5000.0, "PJ")
    val carlos = Funcionario("Carlos", 4000.0, "PJ")
    val augusto = Funcionario("Augusto", 1800.0, "CLT")

    val repositorio = Repositorio<Funcionario>()

    repositorio.creat(joao.nome, joao)
    repositorio.creat(pedro.nome, pedro)
    repositorio.creat(maria.nome, maria)
    repositorio.creat(carlos.nome, carlos)
    repositorio.creat(augusto.nome, augusto)

    println(repositorio.findById(joao.nome))

    println("*************************")

    repositorio.findAll().forEach { println(it) }

    println("*************************")

    repositorio.remove(carlos.nome)
    repositorio.findAll().forEach { println(it) }
}