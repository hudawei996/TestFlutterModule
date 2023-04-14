package com.htcm.testfluttermodule

import android.app.Application
import com.htcm.testfluttermodule.flutter.channel.BasicMessageChannelHelper
import com.htcm.testfluttermodule.flutter.channel.CommonMethodChannel
import com.htcm.testfluttermodule.flutter.engine.FlutterInit
import io.flutter.embedding.engine.FlutterEngine

class MyApplication : Application() {

    companion object {
        var application: Application? = null
    }

    override fun onCreate() {
        super.onCreate()
        application = this

        FlutterInit.init(this) { engine: FlutterEngine ->
            CommonMethodChannel.initMethodCallHandler()
            BasicMessageChannelHelper.init()
        }
    }
}