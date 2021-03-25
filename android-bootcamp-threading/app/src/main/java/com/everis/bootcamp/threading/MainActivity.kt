package com.everis.bootcamp.threading

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //018 - fazer o handle do clique do botão
        button_load_data.setOnClickListener {
            lauchAsyncTask()
        }
    }


    //013 - Criar função para exibir os dados carregados
    fun showData(list: List<AstrosPeople>?){
        textView_data.text = ""
        list?.forEach{ people ->
            textView_data.append("${people.name} - ${people.craft} \n\n" )
        }
    }

    //014 - Criar função para exibir a ProgressBar
    fun showLoadingIndicator(){
        progressbar_load_indicator.visibility = View.VISIBLE
    }

    //015 - Criar função para esconder a ProgressBar
    fun hideLoadingIndicator(){
        progressbar_load_indicator.visibility = View.GONE
    }

    //017 - Criar função para lançar a Task
    fun lauchAsyncTask(){
        TaskAstros().execute()
    }

    //016 - Criar classe interna para rodar a tarefa assincrona
    inner class TaskAstros() : AsyncTask<Void, Void, List<AstrosPeople>?>() {
        private val repository = AstrosRepository()

        override fun onPreExecute() {
            super.onPreExecute()
            showLoadingIndicator()
        }

        override fun doInBackground(vararg params: Void?): List<AstrosPeople>? {
            val result = repository.loadData()
            return result?.people
        }

        override fun onPostExecute(result: List<AstrosPeople>?) {
            super.onPostExecute(result)
            hideLoadingIndicator()
            showData(result)
        }
    }
}
