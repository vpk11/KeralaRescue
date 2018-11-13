package `in`.trect.keralarescue.activity

import `in`.trect.idcard.utils.ShowMessage
import `in`.trect.keralarescue.R
import `in`.trect.keralarescue.fragment.WebViewFragment
import `in`.trect.keralarescue.utils.Constants
import android.os.Bundle
import android.os.Handler
//import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
//import android.view.Menu
import android.view.MenuItem
//import com.gmail.samehadar.iosdialog.IOSDialog
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.fragment_web_view.*

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var fragment: WebViewFragment
//    private lateinit var dialog: IOSDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        val menu = nav_view.menu
        menu.getItem(0).isChecked = true
        menu.getItem(0).isCheckable = true

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        fragment = WebViewFragment.newInstance(Constants.KERALA_RESCUE_IN)

        supportFragmentManager.inTransaction {
            add(R.id.webViewContainer, fragment, Constants.KERALA_RESCUE_IN + "Fragment")
        }
    }

    private var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
        var flag = true
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            flag = false
            drawer_layout.closeDrawer(GravityCompat.START)
        }
        if (webview.canGoBack()) {
            flag = false
            webview.goBack()
        }
        if (flag) {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed()
                return
            }
            this.doubleBackToExitPressedOnce = true
            ShowMessage.infoToast(this@HomeActivity, Constants.PRESS_AGAIN_TO_EXIST)
            Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.kerala_rescue -> {
                val fragment = WebViewFragment.newInstance(Constants.KERALA_RESCUE_IN)
                supportFragmentManager.inTransaction {
                    add(R.id.webViewContainer, fragment, Constants.KERALA_RESCUE_IN + "Fragment")
                }
                item.isCheckable = true
                item.isChecked = true
            }
            R.id.cm_distress_relief_fund -> {
                val fragment = WebViewFragment.newInstance(Constants.CM_DISTRESS_RELIEF_FUND)
                supportFragmentManager.inTransaction {
                    add(R.id.webViewContainer, fragment, Constants.CM_DISTRESS_RELIEF_FUND + "Fragment")
                }
                item.isCheckable = true
                item.isChecked = true
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        fragmentTransaction.commit()
    }
}
