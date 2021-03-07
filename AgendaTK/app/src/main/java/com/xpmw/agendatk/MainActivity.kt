package com.xpmw.agendatk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val rvList: RecyclerView by lazy {
        findViewById(R.id.rv_list)
    }

    private val adapter = ContactAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.drawer_menu)

        initDrawer()
        bindViews()
        updateList()
    }

    private fun initDrawer(){
        val drawerLauot = findViewById<View>(R.id.drawer_lauout) as DrawerLayout
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(this, drawerLauot, toolbar, R.string.open_drawer, R.string.close_drawer)
        drawerLauot.addDrawerListener(toggle)
        toggle.syncState()
    }


    private fun bindViews(){
        rvList.adapter = adapter
        rvList.layoutManager = LinearLayoutManager(this)
    }

    private fun updateList(){
        adapter.updateList(
            arrayListOf(
                Contact(
                    "Eryck Kaique",
                    "(63) 89999-9999",
                    "img.png"
                ),
                Contact(
                    "João Jose",
                    "(63) 89999-9999",
                    "img.png"
                )
            )
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    private fun showToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId){
            R.id.item_menu_1 -> {
                showToast("Menu 1")
                return true
            }
            R.id.item_menu_2 -> {
                showToast("Menu 2")
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}