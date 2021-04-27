package br.com.xpmw.firestoreproject.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import br.com.xpmw.firestoreproject.R
import br.com.xpmw.firestoreproject.model.Users
import br.com.xpmw.firestoreproject.utils.Const
import br.com.xpmw.firestoreproject.utils.Const.EXTRA_DATA
import br.com.xpmw.firestoreproject.utils.Const.PATH_COLLECTION
import br.com.xpmw.firestoreproject.utils.Const.REQ_EDIT
import br.com.xpmw.firestoreproject.utils.Const.setTimeStamp
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_users.*

class UserActivity : AppCompatActivity() {

    private var isEdit = false
    private var users: Users? = null

    val firebaseFirestore = FirebaseFirestore.getInstance()
    private val collectionReference: CollectionReference =
        firebaseFirestore.collection(Const.PATH_COLLECTION)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        isEdit = intent.getBooleanExtra(REQ_EDIT, false)
        users = intent.getParcelableExtra(EXTRA_DATA)

        /* Modo de inserção sem ID
        addUserBtn.setOnClickListener {
            var userName: String = edtName.text.trim().toString()
            var userEmail: String = edtEmail.text.trim().toString()
            var userPhone: String = edtPhone.text.trim().toString()

            addUserToFireBase(userName, userEmail, userPhone)
        }
         */

        addUserBtn.setOnClickListener {
            saveData()
            finish()
        }

        initView()

    }

    private fun initView() {
        if (isEdit) {
            addUserBtn.text = "Atualizar"
            edtName.text = Editable.Factory.getInstance().newEditable(users?.userName)
            edtEmail.text = Editable.Factory.getInstance().newEditable(users?.userEmail)
            edtPhone.text = Editable.Factory.getInstance().newEditable(users?.userPhone)
        }
    }

    private fun saveData() {
        setData(users?.userId)
    }

    private fun setData(id: String?) {
        createUser(id).addOnCompleteListener {
            if (it.isSuccessful) {
                if (isEdit) {
                    Toast.makeText(this, "Dados atualizados com sucesso", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Sucesso na adição do usuário", Toast.LENGTH_SHORT).show()
                }
                finish()
            } else {
                Toast.makeText(this, "Falha ao adicionar usuário", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Error Added data ${it.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun createUser(id: String?): Task<Void> {
        val writeBatch = firebaseFirestore.batch()
        val path = PATH_COLLECTION + setTimeStamp().toString()
        val id = id ?: path
        val userName = edtName.text.toString()
        val userEmail = edtEmail.text.toString()
        val userPhone = edtPhone.text.toString()

        val users = Users(id, userName, userEmail, userPhone)

        writeBatch.set(collectionReference.document(id), users)
        return writeBatch.commit()
    }


    //Conexão antiga para crição do usuário
    fun addUserToFireBase(username: String, useremail: String, userphone: String) {
        val user =
            hashMapOf("userName" to username, "userEmail" to useremail, "userPhone" to userphone)

        firebaseFirestore.collection("users")
            .add(user as Map<String, Any>)
            .addOnCanceledListener { ->
                Toast.makeText(this, "Successful ", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { exeption ->
                Toast.makeText(this, "Failed to add", Toast.LENGTH_SHORT).show()
            }
    }
}