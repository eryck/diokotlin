package br.com.xpmw.myshoppal.activities

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import br.com.xpmw.myshoppal.R
import br.com.xpmw.myshoppal.firestore.FirestoreClass
import br.com.xpmw.myshoppal.model.User
import br.com.xpmw.myshoppal.utils.Constants
import br.com.xpmw.myshoppal.utils.Constants.EXTRA_USER_DETAILS
import br.com.xpmw.myshoppal.utils.Constants.FEMALE
import br.com.xpmw.myshoppal.utils.Constants.GENDER
import br.com.xpmw.myshoppal.utils.Constants.IMAGE
import br.com.xpmw.myshoppal.utils.Constants.MALE
import br.com.xpmw.myshoppal.utils.Constants.MOBILE
import br.com.xpmw.myshoppal.utils.Constants.PICK_IMAGE_REQUEST_CODE
import br.com.xpmw.myshoppal.utils.Constants.READ_STORAGE_PERMISSION_CODE
import br.com.xpmw.myshoppal.utils.Constants.showImageChooser
import br.com.xpmw.myshoppal.utils.GliderLoader
import kotlinx.android.synthetic.main.activity_user_profile.*
import java.io.IOException

class UserProfileActivity : BaseActivity(), View.OnClickListener {

    private lateinit var mUserDetails: User
    private var mSelectedImageFileUri: Uri? = null
    private var mUserProfileImageURL: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)


        if (intent.hasExtra(EXTRA_USER_DETAILS)) {
            mUserDetails = intent.getParcelableExtra(EXTRA_USER_DETAILS)!!
        }

        et_first_name.isEnabled = false
        et_first_name.setText(mUserDetails.firstName)
        et_last_name.isEnabled = false
        et_last_name.setText(mUserDetails.lastName)
        et_email.isEnabled = false
        et_email.setText(mUserDetails.email)

        iv_user_photo.setOnClickListener(this@UserProfileActivity)
        btn_submit.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.iv_user_photo -> {
                    if (ContextCompat.checkSelfPermission(
                            this, Manifest.permission.READ_EXTERNAL_STORAGE
                        )
                        == PackageManager.PERMISSION_GRANTED
                    ) {
                        //showErrorSnackBar("You already have the storage permission", false)
                        showImageChooser(this)
                    } else {
                        ActivityCompat.requestPermissions(
                            this,
                            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                            Constants.READ_STORAGE_PERMISSION_CODE
                        )
                    }
                }

                R.id.btn_submit -> {
                    if (validationUserProfileDetails()) {
                        showProgressDialog(resources.getString(R.string.please_wait))
                        if (mSelectedImageFileUri != null)
                            FirestoreClass().uploadImageToCloudStorage(this, mSelectedImageFileUri)
                        else{
                            updateUserProfileDetails()
                        }
                    }
                }
            }
        }
    }

    private fun updateUserProfileDetails() {

        val userHashMap = HashMap<String, Any>()
        val mobileNumber = et_mobile_number.text.toString().trim() { it <= ' ' }
        val gender = if (rb_male.isSelected) {
            MALE
        } else {
            FEMALE
        }
        if (mobileNumber.isNotEmpty()) {
            userHashMap[MOBILE] = mobileNumber.toLong()
        }

        if (mUserProfileImageURL.isNotEmpty()) {
            userHashMap[IMAGE] = mUserProfileImageURL
        }
        userHashMap[GENDER] = gender

        //showProgressDialog(resources.getString(R.string.please_wait))

        FirestoreClass().updateUserProfileData(this, userHashMap)
        //showErrorSnackBar("Your details are valid. You can update them", false)
    }

    fun userProfileUpdateSuccess() {
        hideProgressDialog()
        Toast.makeText(
            this,
            resources.getString(R.string.msg_profile_update_success),
            Toast.LENGTH_SHORT
        ).show()

        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == READ_STORAGE_PERMISSION_CODE) {
            if (grantResults.isEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //showErrorSnackBar("The storage permission is granted", false)
                showImageChooser(this)
            } else {
                Toast.makeText(
                    this,
                    resources.getString(R.string.read_storage_permission_denied),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == PICK_IMAGE_REQUEST_CODE) {
                if (data != null) {
                    try {
                        mSelectedImageFileUri = data.data!!
                        //iv_user_photo.setImageURI(selectedImageFireUri)
                        GliderLoader(this).loadUserPicture(mSelectedImageFileUri!!, iv_user_photo)
                    } catch (e: IOException) {
                        e.printStackTrace()
                        Toast.makeText(
                            this,
                            resources.getString(R.string.image_selection_failed),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        } else if (resultCode == Activity.RESULT_CANCELED) {
            Log.e("Request Cancelled", "Image selection cancelled")
        }
    }

    private fun validationUserProfileDetails(): Boolean {
        return when {
            TextUtils.isEmpty(et_mobile_number.text.toString().trim() { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_mobile_number), true)
                false
            }
            else -> {
                true
            }
        }
    }

    fun imageUploadSuccess(imageURL: String) {
        hideProgressDialog()
        //Toast.makeText(this, "Your image is upload successfully. Image URL is $imageURL", Toast.LENGTH_SHORT).show()
        mUserProfileImageURL = imageURL
        updateUserProfileDetails()
    }

}