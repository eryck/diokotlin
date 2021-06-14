package br.com.xpmw.myshoppal.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Product (
    val user_id: String = "",
    val user_name: String = "",
    val title: String = "",
    val price: String = "",
    val description: String = "",
    val stock_quantity: String = "",
    val id: String = ""
): Parcelable