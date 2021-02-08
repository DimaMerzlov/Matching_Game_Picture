package dima_merzlov.com.matching_game_picture


import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.webkit.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dima_merzlov.com.matching_game_picture.R.drawable.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var isConnected = false
        var conectivityManag =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activityNetwork = conectivityManag.activeNetworkInfo
        isConnected = activityNetwork?.isConnectedOrConnecting == true
        if (!isConnected){
            var intent = Intent(this@MainActivity, GameActivity::class.java)
            startActivity(intent)
        }
        var web = webView
        var webSettings = web.settings
        webSettings.javaScriptEnabled = true
        web.webViewClient = object : WebViewClient() {
            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                var intent = Intent(this@MainActivity, GameActivity::class.java)
                startActivity(intent)
                Log.i("KEYWORD", "onReceivedError")
                super.onReceivedError(view, request, error)
            }
        }
        web.loadUrl("https://scnddmn.com/7vZTBtvQ")

    }

    override fun onBackPressed() {
        if (webView.canGoBack())webView.goBack()
        else super.onBackPressed()

    }
}