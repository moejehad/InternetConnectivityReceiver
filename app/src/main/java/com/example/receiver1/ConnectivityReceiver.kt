package com.example.receiver1

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager

class ConnectivityReceiver : BroadcastReceiver(){

    override fun onReceive(context: Context?, p1: Intent?) {
        if (connectionReciverListiner != null){
            connectionReciverListiner!!.onNetworkConnected(checkConnection(context!!))
        }
    }

    private fun checkConnection(context: Context) : Boolean{
        val connectMan = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = connectMan.activeNetworkInfo
        return info != null && info.isConnectedOrConnecting
    }

    interface ConnectionReciverListiner{
        fun  onNetworkConnected(isConnected:Boolean)
    }

    companion object{
        var connectionReciverListiner:ConnectionReciverListiner? = null
    }

}