package `in`.trect.idcard.utils

/**
 * @author Trect
 * Last Modified : Jun 11 2018
 */

import android.content.Context
import android.widget.Toast
import com.valdesekamdem.library.mdtoast.MDToast

class ShowMessage {

    companion object {


        fun toast(context: Context, str: String) {
            Toast.makeText(context, str, Toast.LENGTH_SHORT).show()
        }

        fun successToast(context: Context, message: String) {
            val mdToast = MDToast.makeText(context, message, MDToast.LENGTH_SHORT, MDToast.TYPE_SUCCESS)

            mdToast.show()
        }

        fun infoToast(context: Context, message: String) {
            val mdToast = MDToast.makeText(context, message, MDToast.LENGTH_SHORT, MDToast.TYPE_INFO)
            mdToast.show()
        }

        fun warningToast(context: Context, message: String) {
            val mdToast = MDToast.makeText(context, message, MDToast.LENGTH_SHORT, MDToast.TYPE_WARNING)
            mdToast.show()
        }

        fun errorToast(context: Context, message: String) {
            val mdToast = MDToast.makeText(context, message, MDToast.LENGTH_LONG, MDToast.TYPE_ERROR)
            mdToast.show()
        }
    }
}