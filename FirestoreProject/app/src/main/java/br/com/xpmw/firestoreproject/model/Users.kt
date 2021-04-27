package br.com.xpmw.firestoreproject.model

import android.os.Parcelable
import com.google.firebase.firestore.DocumentReference
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Users(
    var userId: String = "0",
    var userName: String? = null,
    var userEmail: String? = null,
    var userPhone: String? = null
): Parcelable
