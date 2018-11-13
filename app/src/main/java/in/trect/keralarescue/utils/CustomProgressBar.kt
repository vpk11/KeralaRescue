package `in`.trect.keralarescue.utils

/**
 * @author Trect
 * Last Modified : AUG 15 2018
 */

//import `in`.trect.keralarescue.R
import android.content.Context
import com.gmail.samehadar.iosdialog.IOSDialog



class CustomProgressBar {
    companion object {
        fun showProgressBar(context: Context): IOSDialog {
            return IOSDialog.Builder(context)
                    .setTitle(Constants.LOADING)
//                    .setTitleColorRes(R.color.white)
                    .build()
        }
    }
}