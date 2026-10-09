
package mw.chiwamba.newsawards

import android.app.Activity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.webkit.WebResourceRequest
import android.content.Intent
import android.net.Uri
import android.view.ViewGroup
import android.webkit.WebSettings

class MainActivity : Activity() {

    private lateinit var webView: WebView

    private val websiteUrl =
        "https://lloydbandaofficial-cyber.github.io/Chiwamba-news-awards-/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        webView = WebView(this)
        webView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.settings.cacheMode = WebSettings.LOAD_DEFAULT

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView,
                request: WebResourceRequest
            ): Boolean {
                val uri = request.url
                val host = uri.host ?: return true

                if (host == "lloydbandaofficial-cyber.github.io") {
                    return false
                }

                startActivity(Intent(Intent.ACTION_VIEW, uri))
                return true
            }
        }

        webView.webChromeClient = WebChromeClient()
        setContentView(webView)

        if (savedInstanceState == null) {
            webView.loadUrl(websiteUrl)
        } else {
            webView.restoreState(savedInstanceState)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        webView.saveState(outState)
        super.onSaveInstanceState(outState)
    }

    @Suppress("DEPRECATION")
    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}
