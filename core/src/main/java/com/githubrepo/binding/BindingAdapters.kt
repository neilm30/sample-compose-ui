package com.githubrepo.binding

import android.annotation.SuppressLint
import android.net.http.SslError
import android.view.View
import android.view.ViewGroup
import android.webkit.SslErrorHandler
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.*


object BindingAdapters {


    @BindingAdapter("visibility")
    fun setVisibility(view: ViewGroup, visibility: Boolean?) {
        visibility?.let {
            view.visibility = if (it) View.VISIBLE else View.GONE
        }
    }

    @JvmStatic
    @BindingAdapter("loadWebUrl")
    fun setLoadWebUrl(webView: WebView, url: String?) {
        webView.webViewClient = object : WebViewClient() {
            @SuppressLint("WebViewClientOnReceivedSslError")
            override fun onReceivedSslError(
                view: WebView?, handler: SslErrorHandler, error: SslError?
            ) {
                handler.cancel()
            }
        }
        webView.webChromeClient = WebChromeClient()
        webView.loadUrl(url.toString())
    }
}