package one.digitalinnovation.digionebank

class Analsita(nome: String, cpf: String, salario: Double) : Funcionario(nome, cpf, salario) {
    override fun calculoAuxilio() = salario * 0.1
}