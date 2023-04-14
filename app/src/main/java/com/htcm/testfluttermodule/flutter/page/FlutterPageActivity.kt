package com.htcm.testfluttermodule.flutter.page

import android.util.Log
import com.htcm.testfluttermodule.flutter.engine.FlutterInit
import io.flutter.embedding.android.FlutterActivity

class FlutterPageActivity : FlutterActivity() {
    companion object {
        private const val TAG = "FlutterPageActivity"
    }

    override fun getCachedEngineId(): String {
        Log.i(TAG, "getCachedEngineId: 调用")
        return FlutterInit.ENGINE_ID
    }
}