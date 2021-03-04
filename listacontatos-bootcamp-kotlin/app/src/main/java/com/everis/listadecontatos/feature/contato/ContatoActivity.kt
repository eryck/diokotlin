package com.everis.listadecontatos.feature.contato

import android.os.Bundle
import android.view.View
import com.everis.listadecontatos.R
import com.everis.listadecontatos.application.ContatoApplication
import com.everis.listadecontatos.bases.BaseActivity
import com.everis.listadecontatos.feature.listacontatos.model.ContatosVO
import com.everis.listadecontatos.singleton.ContatoSingleton
import kotlinx.android.synthetic.main.activity_contato.*
import kotlinx.android.synthetic.main.activity_contato.toolBar
import kotlin.concurrent.thread

class ContatoActivity : BaseActivity() {

    private var idContato: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contato)
        setupToolBar(toolBar, "Contato",true)
        setupContato()
        btnSalvarConato.setOnClickListener { onClickSalvarContato() }
    }

    private fun setupContato(){

        idContato = intent.getIntExtra("index",-1)
        if (idContato == -1){
            btnExcluirContato.visibility = View.GONE
            return
        }

        //etNome.setText(ContatoSingleton.lista[index].nome)
        //etTelefone.setText(ContatoSingleton.lista[index].telefone)
        progress.visibility = View.VISIBLE
        Thread(Runnable {
            Thread.sleep(500)

            var lista : List<ContatosVO> = ContatoApplication.instance.helperDB?.buscarContatos("$idContato", true) ?: return@Runnable
            var contato : ContatosVO = lista.getOrNull(0) ?: return@Runnable

            runOnUiThread{
                etNome.setText(contato.nome)
                etTelefone.setText(contato.telefone)

                progress.visibility = View.GONE
            }

        }).start()

    }

    private fun onClickSalvarContato(){

        progress.visibility = View.VISIBLE

        val nome = etNome.text.toString()
        val telefone = etTelefone.text.toString()
        val contato = ContatosVO(
            idContato,
            nome,
            telefone
        )

        Thread(Runnable {

            if(idContato == -1) {
                //ContatoSingleton.lista.add(contato)
                ContatoApplication.instance.helperDB?.salvarContato(contato)
            }else{
                ContatoApplication.instance.helperDB?.updateContato(contato)
            }

            runOnUiThread {
                progress.visibility = View.GONE
                finish()
            }

        }).start()


    }

    fun onClickExcluirContato(view: View) {

        if(idContato > -1) {
            progress.visibility = View.VISIBLE
            Thread(Runnable {
                ContatoApplication.instance.helperDB?.deletarContato(idContato)
                runOnUiThread {
                    progress.visibility = View.GONE
                    finish()
                }
            }).start()
        }
    }
}
