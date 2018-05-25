package sgcf.zz.com.pritice.activity

import android.annotation.SuppressLint
import android.net.http.SslError
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.webkit.*
import sgcf.zz.com.pritice.R

class ArticleDetailActivity : AppCompatActivity() {

    val tag = "ArticleDetailActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)
        val webView = findViewById<WebView>(R.id.web_view)
        initWebView(webView)
        val url = intent.getStringExtra("url")

//        fun MutableList<Any>.swap(index1: Int, index2: Int) {
//            val tmp = this[index1] // “this”对应该列表
//            this[index1] = this[index2]
//            this[index2] = tmp
//        }
//
//        val l = mutableListOf(1, 2, 3, Address("s"))
//        for (item in l) Log.e("list", "" + item.toString())
//
//
//        l.swap(0, 2) // “swap()”内部的“this”得到“l”的值
//
//        for (item: Any in l) {
//            print(item)
//            Log.e("list", "" + item.toString())
//        }

        webView.loadUrl(url)

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {

                view.loadUrl(url)
                //                return true;
                return super.shouldOverrideUrlLoading(view, url)
            }

            override fun onReceivedSslError(view: WebView, handler: SslErrorHandler, error: SslError) {
                handler.proceed()
            }
        }

        webView.webChromeClient = object : WebChromeClient() {
            override fun onReceivedTitle(view: WebView?, title: String?) {
                super.onReceivedTitle(view, title)
                Log.e(tag, title)
            }

            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
//                Log.e(tag, newProgress.toString());
            }
        }


    }

    @SuppressLint("JavascriptInterface", "SetJavaScriptEnabled", "AddJavascriptInterface")
    private fun initWebView(webView: WebView) {
        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true

        webSettings.allowFileAccess = false
        webSettings.allowFileAccessFromFileURLs = false
        webSettings.allowUniversalAccessFromFileURLs = false

        webSettings.allowContentAccess = true
        webSettings.domStorageEnabled = true
        webSettings.setAppCacheEnabled(true)


//        webView.addJavascriptInterface(MyJavaScriptObject(this), "JsToAndroid")

        webSettings.loadsImagesAutomatically = Build.VERSION.SDK_INT >= 19

        if (Build.VERSION.SDK_INT >= 21) {
            webSettings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }
    }
}




