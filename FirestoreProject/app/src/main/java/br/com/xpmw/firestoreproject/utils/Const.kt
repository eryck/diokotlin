package br.com.xpmw.firestoreproject.utils

object Const {
    //Firestore
    val PATH_COLLECTION = "users"
    val NAME_USER = "userName"

    //Intent comunication
    val EXTRA_DATA = "extra_data"
    val REQ_EDIT = "req_edit"

    fun setTimeStamp(): Long{
        val time = (-1 * System.currentTimeMillis())
        return time
    }
}