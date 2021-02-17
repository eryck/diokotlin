package one.digitalinnovation.digionebank.testes

import one.digitalinnovation.digionebank.Cliente
import one.digitalinnovation.digionebank.ClienteTipo

fun main(){
    val clin01 = Cliente("Carlos", "147.258.369-85", ClienteTipo.PF, "12345")
    println(clin01.toString())
    TesteAuteticacao().autentica(clin01)
}