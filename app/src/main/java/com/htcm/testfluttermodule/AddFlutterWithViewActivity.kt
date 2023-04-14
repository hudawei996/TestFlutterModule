package com.htcm.testfluttermodule

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import io.flutter.embedding.android.FlutterView
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.embedding.engine.loader.FlutterLoader
import io.flutter.embedding.engine.renderer.FlutterUiDisplayListener
import io.flutter.view.FlutterMain

/**
 * 参考页面:https://zhuanlan.zhihu.com/p/104450100
 */
class AddFlutterWithViewActivity : AppCompatActivity() {

    private lateinit var flutterView: FlutterView
    private lateinit var flutterContainer: FrameLayout
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_flutter_with_view)
        flutterView = findViewById(R.id.flutterView)
        flutterContainer = findViewById(R.id.flutterViewContainer)
        progressBar = findViewById(R.id.progress)
    }

    fun loadFlutterView(v: View) {
        loadFlutterView()
        createFlutterView()
    }

    private fun loadFlutterView() {
        // 创建引擎
        val flutterEngine = FlutterEngine(this)
        // 路由带参数
        val route = "1001?{\"imageUrl\":\"https://dfsimg1.hqewimg.com/group1/M00/12/33/wKhk7l2JuNuAcq6RAAA4guj_t9E075.jpg\"}"
        flutterEngine.navigationChannel.setInitialRoute(route)
        flutterEngine.dartExecutor.executeDartEntrypoint(
            DartExecutor.DartEntrypoint.createDefault()
        )
        // 渲染flutter页面
        flutterView.attachToFlutterEngine(flutterEngine)
    }

    private fun createFlutterView() {
        // 通过FlutterView引入Flutter编写的页面
        val flutterView = FlutterView(this)
        val lp = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        val flContainer = findViewById<FrameLayout>(R.id.flutterViewContainer)
        flutterView.addOnFirstFrameRenderedListener(
            object : FlutterUiDisplayListener {
                override fun onFlutterUiDisplayed() {
                    progressBar.visibility = View.GONE
                }

                override fun onFlutterUiNoLongerDisplayed() {
                    progressBar.visibility = View.VISIBLE
                }
            }
        )

        flContainer.addView(flutterView, lp)

        //  创建引擎
        val flutterEngine = FlutterEngine(this)

        // 设置页面路由
//        flutterEngine.navigationChannel.setInitialRoute("1001")
//        flutterEngine.dartExecutor.executeDartEntrypoint(
//            DartExecutor.DartEntrypoint.createDefault()
//        )
        //另一种写法,其实是不同的,这是跳转路由,上边的是发送消息吧,还需要研究一下®
        flutterEngine.dartExecutor.executeDartEntrypoint(
            DartExecutor.DartEntrypoint(
                FlutterMain.findAppBundlePath(),//过时了
//                FlutterLoader().findAppBundlePath(),//这个用了会崩溃
            "main"
            )
        )

        // 关键代码，将Flutter页面显示到FlutterView中
        flutterView.attachToFlutterEngine(flutterEngine)
    }
}