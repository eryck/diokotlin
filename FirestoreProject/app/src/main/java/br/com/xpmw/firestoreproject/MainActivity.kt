package br.com.xpmw.firestoreproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

   lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun addUserToFireBase(username: String, useremail:String, userphone:String){
        val user = hashMapOf("UserName" to username, "UserEmail" to useremail, "UserPhone" to userphone)

        firestore.collection("users")
                .add(user as Map<String, Any>)
                .addOnCanceledListener {  ->
                    Toast.makeText(this, "Successful ", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {exeption ->
                    Toast.makeText(this, "Failed to add", Toast.LENGTH_SHORT).show()
                }
    }
}