package com.everis.listadecontatos.helpers

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.everis.listadecontatos.feature.listacontatos.model.ContatosVO

class HelperDB(
    context: Context
) : SQLiteOpenHelper(context, NOME_BANCO, null, VERSAO_ATUAL) {

    companion object{
        private val NOME_BANCO = "contato.db"
        private val VERSAO_ATUAL = 2

    }

    val TABLE_NAME = "contato"
    val COLUMNS_ID = "id"
    val COLUMNS_NOME = "nome"
    val COLUMNS_TELEFONE = "telefone"
    val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
    val CREATE_TABLE = "CREATE TABLE $TABLE_NAME (" +
            "$COLUMNS_ID INTEGER NOT NULL," +
            "$COLUMNS_NOME TEXT NOT NULL," +
            "$COLUMNS_TELEFONE TEXT NOT NULL," +
            "" +
            "PRIMARY KEY ($COLUMNS_ID AUTOINCREMENT)" +
            ")"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        if(oldVersion != newVersion){
            //update da sua table ou criar novas tabelas
            db?.execSQL(DROP_TABLE)
        }
        onCreate(db)
    }

    fun buscarContatos(busca: String): List<ContatosVO>{
        //salvarContato(ContatosVO(0, "GLUTI", "123456"))
        val db :SQLiteDatabase = readableDatabase ?: return mutableListOf()
        val lista : MutableList<ContatosVO> = mutableListOf<ContatosVO>()
        val sql = "SELECT * FROM $TABLE_NAME WHERE $COLUMNS_NOME LIKE ?"
        var buscarComPercetual = "%$busca%"
        val cursor :Cursor = db.rawQuery(sql, arrayOf(buscarComPercetual))
        if(cursor == null){
            db.close()
            return mutableListOf()
        }
        while (cursor.moveToNext()){
            var cotato = ContatosVO(
                    cursor.getInt(cursor.getColumnIndex(COLUMNS_ID)),
                    cursor.getString(cursor.getColumnIndex(COLUMNS_NOME)),
                    cursor.getString(cursor.getColumnIndex(COLUMNS_TELEFONE))
            )
            lista.add(cotato)
        }
        db.close()
        return lista
    }

    fun salvarContato(contato: ContatosVO){
        val db :SQLiteDatabase = writableDatabase ?: return
        /**
        val sql = "INSERT INTO $TABLE_NAME ($COLUMNS_NOME, $COLUMNS_TELEFONE) VALUES (?,?)"
        val array : Array<String> = arrayOf(contato.nome, contato.telefone)
        db.execSQL(sql, array)
        */
        val content = ContentValues()
        content.put(COLUMNS_NOME, contato.nome)
        content.put(COLUMNS_TELEFONE, contato.telefone)
        db.insert(TABLE_NAME, null, content)
        db.close()
    }
}