package dev.phoedo.blockingwebbrowser

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val requestUrl = "http://phoedo.url.ph/test.html"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        browser.settings.apply {
            builtInZoomControls = true
            javaScriptEnabled = true
            javaScriptCanOpenWindowsAutomatically = true

        }
        val client = BrowserBlockerClient().apply {
            blockTracking = true
            filters.add(BrowserBlockerClient.Filter().apply {
                urlListToAvoid.addAll(
                    listOf(
                        "https://connect.facebook.net/*".toRegex(),
                        "https://www.facebook.com/tr/*".toRegex()
                    )
                )
            })
        }
        browser.webViewClient = client
        browser.loadUrl(requestUrl)
    }


}
