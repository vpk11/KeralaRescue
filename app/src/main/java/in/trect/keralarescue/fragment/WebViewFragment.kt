package `in`.trect.keralarescue.fragment


import `in`.trect.keralarescue.R
//import `in`.trect.keralarescue.utils.Constants
import `in`.trect.keralarescue.utils.CustomProgressBar
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import com.gmail.samehadar.iosdialog.IOSDialog
import kotlinx.android.synthetic.main.fragment_web_view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class WebViewFragment : Fragment() {

    private var link: String? = null
    private lateinit var dialog: IOSDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            link = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_web_view, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if(savedInstanceState == null){
            val cookieManager = CookieManager.getInstance()

            CookieSyncManager.createInstance(activity)
            cookieManager.setAcceptCookie(true)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cookieManager.setAcceptThirdPartyCookies(webview,true)
            }
            CookieSyncManager.getInstance().sync()
//        val cookieStr = cookieManager.getCookie(Constants.BASE_URL+link)
            webview.loadUrl(link)
            val webSettings = webview.settings
            webSettings.cacheMode = WebSettings.LOAD_DEFAULT
            webSettings.domStorageEnabled = true
            webSettings.allowFileAccess = true
            webSettings.allowContentAccess = true
            webSettings.allowFileAccessFromFileURLs = true
            webSettings.javaScriptEnabled = true
            pageLoadStatus()
        }
    }

    private fun pageLoadStatus() {

        webview.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                dialog.dismiss()
                super.onPageFinished(view, url)


            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                dialog = CustomProgressBar.showProgressBar(context as Context)
                dialog.show()
                dialog.setCancelable(false)
                super.onPageStarted(view, url, favicon)

            }

            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                Log.e("In OverRide", "true")
                view?.loadUrl(url)
                return true
            }

            override fun onReceivedHttpError(view: WebView?, request: WebResourceRequest?, errorResponse: WebResourceResponse?) {

                Log.e("Error Req", errorResponse.toString())
                super.onReceivedHttpError(view, request, errorResponse)

            }

        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @return A new instance of fragment WebViewFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String) =
                WebViewFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
//                        putString(ARG_PARAM2, param2)
                    }
                }
    }

}
