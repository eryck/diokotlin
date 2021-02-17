package one.digitalinnovation.digionebank.testes

import one.digitalinnovation.digionebank.Analsita
import one.digitalinnovation.digionebank.Funcionario
import one.digitalinnovation.digionebank.Gerente


fun main(){

    val eryck = Analsita("Eryck", "123.789.456.85", 3000.00)
    ImprimeRelatorioFuncionario.imprime(eryck)

    val kaique = Gerente("Kaique", "123.789.456.85", 5000.00)
    ImprimeRelatorioFuncionario.imprime(kaique)

}