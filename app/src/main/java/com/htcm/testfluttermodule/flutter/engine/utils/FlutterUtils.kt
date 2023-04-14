package com.htcm.testfluttermodule.flutter.engine.utils

import com.htcm.testfluttermodule.flutter.engine.plugin.MyFlutterPlugin
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.plugins.FlutterPlugin


/**
 * @date 2022/8/30
 * @author mxb
 * @desc
 * @desired
 */
class FlutterUtils {
    companion object {
        fun getPlugin(engine: FlutterEngine?): MyFlutterPlugin? {
            if (engine != null) {
                try {
                    val pluginClass =
                        Class.forName("com.htcm.testfluttermodule.flutter.engine.plugin.MyFlutterPlugin") as Class<out FlutterPlugin?>
                    return engine.plugins[pluginClass] as MyFlutterPlugin?
                } catch (t: Throwable) {
                    t.printStackTrace()
                }
            }
            return null
        }
    }

}