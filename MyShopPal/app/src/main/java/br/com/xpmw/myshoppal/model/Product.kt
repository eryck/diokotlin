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
    var stock_quantity: String = "",
    val image: String = "",
    var product_id: String = ""
): Parcelable