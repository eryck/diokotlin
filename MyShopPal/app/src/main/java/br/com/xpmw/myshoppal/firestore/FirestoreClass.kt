package br.com.xpmw.myshoppal.firestore

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.util.Log
import androidx.fragment.app.Fragment
import br.com.xpmw.myshoppal.model.*
import br.com.xpmw.myshoppal.ui.activities.*
import br.com.xpmw.myshoppal.ui.fragments.DashboardFragment
import br.com.xpmw.myshoppal.ui.fragments.OrdersFragment
import br.com.xpmw.myshoppal.ui.fragments.ProductsFragment
import br.com.xpmw.myshoppal.ui.fragments.SoldProductsFragment
import br.com.xpmw.myshoppal.utils.Constants.ADDRESS
import br.com.xpmw.myshoppal.utils.Constants.CART_ITEMS
import br.com.xpmw.myshoppal.utils.Constants.LOGGED_IN_USERNAME
import br.com.xpmw.myshoppal.utils.Constants.MYSHOPPAL_PREFERENCES
import br.com.xpmw.myshoppal.utils.Constants.ORDERS
import br.com.xpmw.myshoppal.utils.Constants.PRODUCTS
import br.com.xpmw.myshoppal.utils.Constants.PRODUCT_ID
import br.com.xpmw.myshoppal.utils.Constants.SOLD_PRODUCTS
import br.com.xpmw.myshoppal.utils.Constants.STOCK_QUANTITY
import br.com.xpmw.myshoppal.utils.Constants.USERS
import br.com.xpmw.myshoppal.utils.Constants.USER_ID
import br.com.xpmw.myshoppal.utils.Constants.getFileExtension
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class FirestoreClass {
    private val mFirestore = FirebaseFirestore.getInstance()

    fun registerUser(activity: RegisterActivity, userInfo: User) {
        mFirestore.collection(USERS)
            .document(userInfo.id)
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {
                activity.userRegistrationSuccess()
            }
            .addOnFailureListener { e ->
                activity.hideProgressDialog()
                Log.e(activity.javaClass.simpleName, "Error while registration the user", e)
            }
    }

    fun getCurrentUserID(): String {
        val currentUser = FirebaseAuth.getInstance().currentUser

        var currentUserID = ""
        if (currentUser != null) {
            currentUserID = currentUser.uid
        }

        return currentUserID
    }

    fun getUserDetails(activity: Activity) {
        mFirestore.collection(USERS)
            .document(getCurrentUserID())
            .get()
            .addOnSuccessListener { document ->
                Log.i(activity.javaClass.simpleName, document.toString())

                val user = document.toObject(User::class.java)!!

                val sharedPreferences = activity.getSharedPreferences(
                    MYSHOPPAL_PREFERENCES,
                    Context.MODE_PRIVATE
                )

                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                //Key: logged_in_username
                //Value: first name and last name
                editor.putString(
                    LOGGED_IN_USERNAME,
                    "${user.firstName} ${user.lastName}"
                )
                editor.apply()

                when (activity) {
                    is LoginActivity -> {
                        activity.userLoggedInSuccess(user)
                    }
                    is SettingsActivity -> {
                        activity.userDetailsSuccess(user)
                    }
                }
            }
            .addOnFailureListener { e ->
                when (activity) {
                    is LoginActivity -> {
                        activity.hideProgressDialog()
                    }
                    is SettingsActivity -> {
                        activity.hideProgressDialog()
                    }
                }
                Log.e(activity.javaClass.simpleName, "Error while getting user details.", e)
            }
    }

    fun updateUserProfileData(activity: Activity, userHashMap: HashMap<String, Any>) {
        mFirestore.collection(USERS)
            .document(getCurrentUserID())
            .update(userHashMap)
            .addOnSuccessListener {
                when (activity) {
                    is UserProfileActivity -> {
                        activity.userProfileUpdateSuccess()
                    }
                }
            }
            .addOnFailureListener {
                when (activity) {
                    is UserProfileActivity -> {
                        activity.hideProgressDialog()
                    }
                }

                Log.e(
                    activity.javaClass.simpleName, "Error while updating the user details "
                )
            }
    }

    fun uploadImageToCloudStorage(activity: Activity, imageFileUri: Uri?, imageType: String) {
        val sRef: StorageReference = FirebaseStorage.getInstance().reference.child(
            imageType + System.currentTimeMillis() + "."
                    + getFileExtension(activity, imageFileUri)
        )
        sRef.putFile(imageFileUri!!).addOnSuccessListener { taskSnapshot ->
            //The image upload is success
            Log.e(
                "Firebase Image URL",
                taskSnapshot.metadata!!.reference!!.downloadUrl.toString()
            )
            //Get the download url from the task snapshot
            taskSnapshot.metadata!!.reference!!.downloadUrl.addOnSuccessListener { uri ->
                Log.e("Download Image URL", uri.toString())
                when (activity) {
                    is UserProfileActivity -> {
                        activity.imageUploadSuccess(uri.toString())
                    }
                    is AddProductActivity -> {
                        activity.imageUploadSuccess(uri.toString())
                    }
                }
            }
        }
            .addOnFailureListener { exception ->
                when (activity) {
                    is UserProfileActivity -> {
                        activity.hideProgressDialog()
                    }
                    is AddProductActivity -> {
                        activity.hideProgressDialog()
                    }
                }
                Log.e(
                    activity.javaClass.simpleName,
                    exception.message,
                    exception
                )
            }
    }

    fun uploadProductDetails(activity: AddProductActivity, productInfo: Product) {
        mFirestore.collection(PRODUCTS)
            .document()
            .set(productInfo, SetOptions.merge())
            .addOnSuccessListener {
                activity.productUploadSuccess()
            }
            .addOnFailureListener { e ->
                activity.hideProgressDialog()
                Log.e(
                    activity.javaClass.simpleName,
                    "Error while uploading the product details.",
                    e
                )
            }
    }

    fun getProductList(fragment: Fragment) {
        mFirestore.collection(PRODUCTS)
            .whereEqualTo(USER_ID, getCurrentUserID())
            .get()
            .addOnSuccessListener { document ->
                Log.e("Product List", document.documents.toString())
                val productsList: ArrayList<Product> = ArrayList()
                for (i in document.documents) {
                    val product = i.toObject(Product::class.java)
                    product!!.product_id = i.id

                    productsList.add(product)
                }
                when (fragment) {
                    is ProductsFragment -> {
                        fragment.successProductListFromFireStore(productsList)
                    }
                }
            }.addOnFailureListener {
                when (fragment) {
                    is ProductsFragment -> {
                        fragment.hideProgressDialog()
                    }
                }
            }
    }

    fun getProductDetails(activity: ProductDetailsActivity, productId: String) {
        mFirestore.collection(PRODUCTS)
            .document(productId)
            .get()
            .addOnSuccessListener { document ->
                Log.e(activity.javaClass.simpleName, document.toString())
                val product = document.toObject(Product::class.java)!!
                //if (product != null) {
                activity.productDetailsSuccess(product)
                //}
            }
            .addOnFailureListener { e ->
                activity.hideProgressDialog()
                Log.e(activity.javaClass.simpleName, "Error while getting the product details.", e)
            }
    }

    fun addCartItems(activity: ProductDetailsActivity, addToCart: CartItem) {
        mFirestore.collection(CART_ITEMS)
            .document()
            .set(addToCart, SetOptions.merge())
            .addOnSuccessListener {
                activity.addToCartSuccess()
            }
            .addOnFailureListener { e ->
                activity.hideProgressDialog()
                Log.e(
                    activity.javaClass.simpleName,
                    "Error while creating the document for cart item.",
                    e
                )
            }
    }


    fun deleteProduct(fragment: ProductsFragment, productId: String) {
        mFirestore.collection(PRODUCTS)
            .document(productId)
            .delete()
            .addOnSuccessListener {
                fragment.productDeleteSuccess()
            }
            .addOnFailureListener { e ->
                fragment.hideProgressDialog()
                Log.e(
                    fragment.requireActivity()
                        .javaClass.simpleName,
                    "Error while deleting the product",
                    e
                )
            }
    }

    fun getCatList(activity: Activity) {
        mFirestore.collection(CART_ITEMS)
            .whereEqualTo(USER_ID, getCurrentUserID())
            .get()
            .addOnSuccessListener { document ->
                Log.e(activity.javaClass.simpleName, document.documents.toString())
                val list: ArrayList<CartItem> = ArrayList()

                for (i in document.documents) {
                    val cartItem = i.toObject(CartItem::class.java)!!
                    cartItem.id = i.id

                    list.add(cartItem)
                }

                when (activity) {
                    is CartListActivity -> {
                        activity.successCartItemsList(list)
                    }
                    is CheckoutActivity -> {
                        activity.successCartItemsList(list)
                    }
                }
            }
            .addOnFailureListener { e ->
                when (activity) {
                    is CartListActivity -> {
                        activity.hideProgressDialog()
                    }
                    is CheckoutActivity -> {
                        activity.hideProgressDialog()
                    }
                }

                Log.e(
                    activity.javaClass.simpleName,
                    "Erros while getting the cart list items",
                    e
                )
            }
    }

    fun getAddressesList(activity: AddressListActivity) {
        mFirestore.collection(ADDRESS)
            .whereEqualTo(USER_ID, getCurrentUserID())
            .get()
            .addOnSuccessListener { document ->
                Log.e(activity.javaClass.simpleName, document.documents.toString())
                val addressList: ArrayList<Address> = ArrayList()
                for (i in document.documents) {
                    val address = i.toObject(Address::class.java)!!
                    address.id = i.id
                    addressList.add(address)
                }
                activity.successAddressListFromFirestore(addressList)
            }
            .addOnFailureListener { e ->
                activity.hideProgressDialog()
                Log.e(
                    activity.javaClass.simpleName,
                    "Error while getting the address", e
                )
            }
    }

    fun updateAllDetails(activity: CheckoutActivity, cartList: ArrayList<CartItem>, order: Order) {
        val writeBatch = mFirestore.batch()

        for (cartItem in cartList) {
            val productHashMap = HashMap<String, Any>()

//            productHashMap[STOCK_QUANTITY] =
//                (cartItem.stock_quantity.toInt() - cartItem.cart_quantity.toInt()).toString()

            val soldProduct = SoldProduct(
                cartItem.product_owner_id,
                cartItem.title,
                cartItem.price,
                cartItem.cart_quantity,
                cartItem.image,
                order.title,
                order.order_datetime,
                order.sub_total_amount,
                order.shipping_charge,
                order.total_amount,
                order.address
            )

            val documentReference = mFirestore.collection(SOLD_PRODUCTS)
                .document(cartItem.product_id)

            writeBatch.set(documentReference, soldProduct)
        }

        for (cartItem in cartList) {
            val documentReference = mFirestore.collection(CART_ITEMS)
                .document(cartItem.id)
            writeBatch.delete(documentReference)
        }

        writeBatch.commit()
            .addOnSuccessListener {
                activity.allDetailsUpdatedSuccessfully()
            }
            .addOnFailureListener { e ->
                activity.hideProgressDialog()
                Log.e(
                    activity.javaClass.simpleName,
                    "Error While updating all the details after order placed",
                    e
                )

            }
    }

    fun getSoldProductsList(fragment: SoldProductsFragment){
        mFirestore.collection(SOLD_PRODUCTS)
            .whereEqualTo(USER_ID, getCurrentUserID())
            .get()
            .addOnSuccessListener { document ->
                val list: ArrayList<SoldProduct> = ArrayList()
                for (i in document.documents){
                    val soldProduct = i.toObject(SoldProduct::class.java)!!
                    soldProduct.id = i.id

                    list.add(soldProduct)
                }

                fragment.successSoldProductList(list)
            }
            .addOnFailureListener { e ->
                fragment.hideProgressDialog()
                Log.e(
                    fragment.javaClass.simpleName,
                    "Error While getting the list of sold products",
                    e
                )

            }
    }

    fun getMyOrderList(fragment: OrdersFragment) {
        mFirestore.collection(ORDERS)
            .whereEqualTo(USER_ID, getCurrentUserID())
            .get()
            .addOnSuccessListener { document ->
                val list: ArrayList<Order> = ArrayList()

                for (i in document.documents) {
                    val orderItem = i.toObject(Order::class.java)!!
                    orderItem.id = i.id
                    list.add(orderItem)
                }
                fragment.popularOrderListInUI(list)
            }
            .addOnFailureListener { e ->
                fragment.hideProgressDialog()
                Log.e(fragment.javaClass.simpleName, "Error while getting the orders list", e)
            }
    }

    fun placeOrder(activity: CheckoutActivity, order: Order) {
        mFirestore.collection(ORDERS)
            .document()
            .set(order, SetOptions.merge())
            .addOnSuccessListener {
                activity.successPlacedOrder()
            }
            .addOnFailureListener { e ->
                activity.hideProgressDialog()
                Log.e(
                    activity.javaClass.simpleName,
                    "Error while placing an order",
                    e
                )
            }
    }

    fun deleteAddress(activity: AddressListActivity, addressId: String) {
        mFirestore.collection(ADDRESS)
            .document(addressId)
            .delete()
            .addOnSuccessListener {
                activity.deleteAddressSuccess()
            }
            .addOnFailureListener { e ->
                activity.hideProgressDialog()
                Log.e(
                    activity.javaClass.simpleName,
                    "Error while deleting the address",
                    e
                )
            }
    }

    fun upDateAddress(activity: AddEditAddressActivity, addressInfo: Address, addressId: String) {
        mFirestore.collection(ADDRESS)
            .document(addressId)
            .set(addressInfo, SetOptions.merge())
            .addOnSuccessListener {
                activity.addUpdateAddressSuccess()
            }
            .addOnFailureListener { e ->
                activity.hideProgressDialog()
                Log.e(
                    activity.javaClass.simpleName,
                    "Error while updating the address"
                )
            }
    }

    fun addAddress(activity: AddEditAddressActivity, addressInfo: Address) {
        mFirestore.collection(ADDRESS)
            .document()
            .set(addressInfo, SetOptions.merge())
            .addOnSuccessListener {
                activity.addUpdateAddressSuccess()
            }
            .addOnFailureListener { e ->
                activity.hideProgressDialog()
                Log.e(
                    activity.javaClass.simpleName,
                    "Error while adding the address",
                    e
                )
            }
    }

    fun updateMyCart(context: Context, cart_id: String, itemHashMap: HashMap<String, Any>) {
        mFirestore.collection(CART_ITEMS)
            .document(cart_id)
            .update(itemHashMap)
            .addOnSuccessListener {
                when (context) {
                    is CartListActivity -> {
                        context.itemUpdateSuccess()
                    }
                }
            }
            .addOnFailureListener { e ->
                when (context) {
                    is CartListActivity -> {
                        context.hideProgressDialog()
                    }
                }
                Log.e(
                    context.javaClass.simpleName,
                    "Error while upgrade the cart item.",
                    e
                )
            }
    }

    fun checkIfItemExistInCart(activity: ProductDetailsActivity, productId: String) {
        mFirestore.collection(CART_ITEMS)
            .whereEqualTo(USER_ID, getCurrentUserID())
            .whereEqualTo(PRODUCT_ID, productId)
            .get()
            .addOnSuccessListener { document ->
                Log.e(activity.javaClass.simpleName, document.documents.toString())
                if (document.documents.size > 0) {
                    activity.productExistsInCart()
                } else {
                    activity.hideProgressDialog()
                }
            }
            .addOnFailureListener { e ->
                activity.hideProgressDialog()
                Log.e(
                    activity.javaClass.simpleName,
                    "Error while checking the existing cart list.",
                    e
                )
            }
    }

    fun removeItemFromCart(context: Context, cart_id: String) {
        mFirestore.collection(CART_ITEMS)
            .document(cart_id)
            .delete()
            .addOnSuccessListener {
                when (context) {
                    is CartListActivity -> {
                        context.itemRemovedSuccess()
                    }
                }
            }
            .addOnFailureListener { e ->
                when (context) {
                    is CartListActivity -> {
                        context.hideProgressDialog()
                    }
                }
                Log.e(
                    context.javaClass.simpleName,
                    "Error while removing the item from the cart list.",
                    e
                )
            }
    }

    fun getAllProductsList(activity: Activity) {
        mFirestore.collection(PRODUCTS)
            .get()
            .addOnSuccessListener { document ->
                Log.e("Products List", document.documents.toString())
                val productsList: ArrayList<Product> = ArrayList()
                for (i in document.documents) {
                    val product = i.toObject(Product::class.java)
                    product!!.product_id = i.id
                    productsList.add(product)
                }
                when (activity) {
                    is CartListActivity -> {
                        activity.successProductListFromFireStore(productsList)
                    }
                    is CheckoutActivity -> {
                        activity.successProductListFromFireStore(productsList)
                    }
                }
            }
            .addOnFailureListener { e ->
                when (activity) {
                    is CartListActivity -> {
                        activity.hideProgressDialog()
                    }
                    is CheckoutActivity -> {
                        activity.hideProgressDialog()
                    }
                }
                Log.e("Get Product List", "Error while getting all product list", e)
            }
    }

    fun getDashboardItemsList(fragment: DashboardFragment) {
        mFirestore.collection(PRODUCTS)
            .get()
            .addOnSuccessListener { documet ->
                Log.e(fragment.javaClass.simpleName, documet.documents.toString())
                val productList: ArrayList<Product> = ArrayList()

                for (i in documet.documents) {
                    val product = i.toObject(Product::class.java)!!
                    product.product_id = i.id
                    productList.add(product)
                }

                fragment.successDashboardItemsList(productList)
            }
            .addOnFailureListener { e ->
                fragment.hideProgressDialog()
                Log.e(fragment.javaClass.simpleName, "Error while getting dashboard items list.", e)
            }
    }

}