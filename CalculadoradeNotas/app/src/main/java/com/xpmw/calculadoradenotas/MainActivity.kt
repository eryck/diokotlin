package com.xpmw.calculadoradenotas

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity)

        val btCalcular :Button = btn_calcular
        val txtResultado : TextView = txt_resultado


        btCalcular.setOnClickListener{
            val nota01: Int = Integer.parseInt(editTextNota01.text.toString())
            val nota02: Int = Integer.parseInt(editTextNota02.text.toString())
            val faltas: Int = Integer.parseInt(editTextFaltas.text.toString())
            val media = (nota01 + nota02)/2

            if(media >= 7 && faltas <= 10){
                txtResultado.setText("Aluno aprovado!" + "\n" + "Média Final: " + media + "\n" + "Faltas: " + faltas)
                txtResultado.setTextColor(Color.GREEN)
            }else{
                txtResultado.setText("Aluno Reprovado!" + "\n" + "Média Final: " + media + "\n" + "Faltas: " + faltas)
                txtResultado.setTextColor(Color.RED)
            }
        }

    }
}