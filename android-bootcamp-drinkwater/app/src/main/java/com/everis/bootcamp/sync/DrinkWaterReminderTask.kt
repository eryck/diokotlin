package com.everis.bootcamp.sync

import android.content.Context
import com.everis.bootcamp.utils.PreferencesUtils

class DrinkWaterReminderTask {
    //001 - Criar uma String constante estatica chamada ACTION_INCREMENT_WATER_COUNT para representar a ação de incrementar o contador
    companion object{
        const val ACTION_INCREMENT_WATER_COUNT = "action-increment-water-count"

        private fun incrementWaterCount(context: Context){
            PreferencesUtils.incrementWaterCount(context)
        }

        fun executeTast(context: Context, action: String?){
            if(action == ACTION_INCREMENT_WATER_COUNT){
                incrementWaterCount(context)
            }
        }
    }

    /*002 -
       - Criar uma função privada chamada incrementWaterCount para incrementar o contador de copo de águas
       - Inclua um Context como parametro da função
       - Chame o metodo incrementWaterCount da classe PreferencesUtils
     */


    /*003
       - Crie um método publico e estatico para executar a tarefa
       - Coloque um Context como parametro e uma string chamada action
       - Quando a action for igual a constante ACTION_INCREMENT_WATER_COUNT chame o método incrementWaterCount desta classe
     */
}


