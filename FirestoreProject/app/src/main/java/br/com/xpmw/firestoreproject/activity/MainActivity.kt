package br.com.xpmw.firestoreproject.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.xpmw.firestoreproject.R
import br.com.xpmw.firestoreproject.UserAdapter
import br.com.xpmw.firestoreproject.UsersModel
import br.com.xpmw.firestoreproject.model.Users
import br.com.xpmw.firestoreproject.utils.Const.NAME_USER
import br.com.xpmw.firestoreproject.utils.Const.PATH_COLLECTION
import br.com.xpmw.firestoreproject.utils.Const.REQ_EDIT
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row_users.*


class MainActivity : AppCompatActivity() {

    private lateinit var userAdapter: FirestoreRecyclerAdapter<Users, UserAdapter.UserAdapterVH>

    private val firebaseFirestore = FirebaseFirestore.getInstance()
    private val collectionReference:CollectionReference = firebaseFirestore.collection(PATH_COLLECTION)

    private val fireQuery = collectionReference.orderBy(NAME_USER)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpRecyclerView()
        initView()

    }

    private fun initView(){
        btnfab.setOnClickListener{
            startActivity(Intent(this, UserActivity::class.java).apply {
                putExtra(REQ_EDIT, false)
            })
        }
    }


    private fun setUpRecyclerView() {

        val firestoreRecyclerOptions: FirestoreRecyclerOptions<Users> = FirestoreRecyclerOptions
            .Builder<Users>()
            .setQuery(fireQuery, Users::class.java)
            .build()

        userAdapter = UserAdapter(this, collectionReference, firestoreRecyclerOptions)
        userAdapter.notifyDataSetChanged()

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = userAdapter
    }

    override fun onStart() {
        super.onStart()
        userAdapter!!.startListening()
    }

    override fun onDestroy() {
        super.onDestroy()
        userAdapter!!.stopListening()
    }
}