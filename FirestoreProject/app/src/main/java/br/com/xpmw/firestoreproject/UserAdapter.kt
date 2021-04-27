package br.com.xpmw.firestoreproject

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import br.com.xpmw.firestoreproject.activity.UserActivity
import br.com.xpmw.firestoreproject.model.Users
import br.com.xpmw.firestoreproject.utils.Const.EXTRA_DATA
import br.com.xpmw.firestoreproject.utils.Const.REQ_EDIT
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.CollectionReference
import kotlinx.android.synthetic.main.row_users.view.*

class UserAdapter(
    private val context: Context,
    private val collectionReference: CollectionReference,
    options: FirestoreRecyclerOptions<Users>):
    FirestoreRecyclerAdapter<Users, UserAdapter.UserAdapterVH>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapterVH {
        return UserAdapterVH(LayoutInflater.from(parent.context).inflate(R.layout.row_users, parent, false))
    }


    override fun onBindViewHolder(viewHolder: UserAdapterVH, position: Int, model: Users) {
        viewHolder.bindItem(model)

        //Delete via View
        /*
        viewHolder.itemView.setOnClickListener {
            model.userId?.let { it1 -> deleteById(it1) }
        }
         */

        viewHolder.itemView.setOnClickListener {
            showDialogMenu(model)
        }

    }

    inner class UserAdapterVH(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bindItem(users: Users){
            itemView.apply {
                var userName = users.userName
                var userEmail = users.userEmail
                var userPhone = users.userPhone

                tvUsername.text = userName
                tvUseremail.text = userEmail
                tvUserphone.text = userPhone
            }
        }
    }

    private fun showDialogMenu(users: Users){
        val builder = AlertDialog.Builder(context, R.style.Theme_MaterialComponents_Light_Dialog_Alert_Bridge)
        val options = arrayOf("Editar", "Deletar")
        builder.setItems(options){ dialog, which ->
            when (which){
                0 -> context.startActivity(Intent(context, UserActivity::class.java).apply {
                    putExtra(REQ_EDIT, true)
                    putExtra(EXTRA_DATA, users)
                })
                1 -> showDialogDelete(users.userId)
            }
        }
        builder.create().show()
    }

    private fun showDialogDelete(id: String){
        val builder = AlertDialog.Builder(context, R.style.Theme_MaterialComponents_Light_Dialog_Alert_Bridge)
            .setTitle("Deletar!")
            .setMessage("Apagar usuário?")
            .setPositiveButton("Deletar"){ dialog, which ->
                deleteById(id)
            }
            .setNegativeButton("Cancelar", null)
        builder.create().show()
    }

    private fun deleteById(id: String){
        collectionReference.document(id)
            .delete()
            .addOnCompleteListener{ Toast.makeText(context, "Successo", Toast.LENGTH_SHORT).show()  }
            .addOnFailureListener{ Toast.makeText(context, "Falha ao deletar", Toast.LENGTH_SHORT).show() }
    }

}