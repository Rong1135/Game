package com.example.game

import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.GridLayout
import android.widget.TextView
import kotlinx.coroutines.*
import java.lang.Runnable

class MainActivity : AppCompatActivity() {

    lateinit var txv: TextView      // 倒數時間
    lateinit var txv2: TextView     // 得分
    lateinit var job: Job
    lateinit var mySurfaceView: MySurfaceView   // 背景繪製

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txv = findViewById(R.id.txv)
        txv2 = findViewById(R.id.txv2)
        mySurfaceView = findViewById(R.id.mysv)

        job = GlobalScope.launch(Dispatchers.Main) {
            for (i in 30 downTo 1) {
                txv.text = "剩餘時間： $i"  // update text
                delay(1000)
            }
            txv.text = "時間到!"

            val canvas: Canvas = mySurfaceView.holder.lockCanvas()
                mySurfaceView.drawSomething(canvas)
            mySurfaceView.holder.unlockCanvasAndPost(canvas)
        }
    }

}