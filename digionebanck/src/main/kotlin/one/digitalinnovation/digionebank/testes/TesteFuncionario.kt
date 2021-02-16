package one.digitalinnovation.digionebank.testes

import one.digitalinnovation.digionebank.Funcionario
import one.digitalinnovation.digionebank.Pessoa
import java.math.BigDecimal

fun main(){
    val eryck = Pessoa("Eryck", "123.456.789-85")

    println(eryck.nome)
    println(eryck.cpf)

    val kaique = Funcionario("Kaique", "123.789.456.85", BigDecimal.valueOf(5000.00))
    println(kaique.nome)
    println(kaique.cpf)
    println(kaique.salario)
}