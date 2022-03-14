package com.example.receiver1

import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), ConnectivityReceiver.ConnectionReciverListiner {

    var snackbar:Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerReceiver(ConnectivityReceiver(), IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun onNetworkConnected(isConnected: Boolean) {
        showMessage(isConnected)
    }

    private fun showMessage(connected: Boolean) {
        if(!connected){
            snackbar = Snackbar.make(findViewById(R.id.root_view),"There is no internet connection",Snackbar.LENGTH_LONG)
            snackbar!!.duration = BaseTransientBottomBar.LENGTH_LONG
            snackbar!!.show()
        }else{
            snackbar!!.dismiss()
        }
    }

    override fun onResume() {
        super.onResume()
        ConnectivityReceiver.connectionReciverListiner = this
    }
}