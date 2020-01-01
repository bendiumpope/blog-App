package com.itex.blogapplication.data.network

import android.content.Context
import android.net.ConnectivityManager
import com.itex.blogapplication.util.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor(context: Context): Interceptor {

    private val applicationContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if(!isInternetAvailable())
            throw NoInternetException("Ensure you are connected to the Internet")

        return chain.proceed(chain.request())

    }

    private fun isInternetAvailable(): Boolean{

        val connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        connectivityManager.activeNetworkInfo.also {
            return it !=null && it.isConnected
        }
    }
}