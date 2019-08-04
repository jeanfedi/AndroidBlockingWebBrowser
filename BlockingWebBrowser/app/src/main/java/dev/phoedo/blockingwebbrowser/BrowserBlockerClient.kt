package dev.phoedo.blockingwebbrowser

import android.os.Build
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi

class BrowserBlockerClient : WebViewClient() {

    var blockTracking = false
    val filters = mutableListOf<Filter>()


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    override fun shouldInterceptRequest(view: WebView?, request: WebResourceRequest?): WebResourceResponse? {
        Log.e("URL", "request url: ${request?.url ?: "null"}")
        if (allowLoading(request?.url?.toString())) {
            Log.e("ALLOWED", "request url: ${request?.url ?: "null"}")
        } else {
            Log.e("BLOCKED", "request url: ${request?.url ?: "null"}")
            return null
        }

        return super.shouldInterceptRequest(view, request)
    }

    override fun shouldInterceptRequest(view: WebView?, url: String?): WebResourceResponse? {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            if (allowLoading(url)) {
                Log.e("ALLOWED", "request url: ${url ?: "null"}")
            } else {
                Log.e("BLOCKED", "request url: ${url ?: "null"}")
                return null

            }
            return super.shouldInterceptRequest(view, url)
        } else {
            return null
        }
    }


    fun allowLoading(url: String?): Boolean {
        url?.let {
            if (blockTracking) {
                for (filter in filters) {
                    for (urlToAvoid in filter.urlListToAvoid) {
                        if (urlToAvoid.containsMatchIn(url)) {
                            return false
                        }
                    }
                }
            }
        }
        return true
    }


    class Filter {
        val urlListToAvoid = mutableListOf<Regex>()
    }


}