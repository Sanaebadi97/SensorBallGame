package info.sanaebadi.sensorballgame

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.util.Log
import android.view.View

class GraphicView(context: Context?) : View(context) {

    companion object {
        private const val TAG = "GraphicView"
    }

    private var paint = arrayOf(Paint(), Paint(), Paint())
    private var radius = 50

    private var x1 = 0
    private var x2 = 0
    private var x3 = 0
    private var y1 = 0
    private var y2 = 0
    private var y3 = 0

    var sensorEventListener: SensorEventListener = object : SensorEventListener {
        @SuppressLint("LogNotTimber")
        override fun onSensorChanged(sensorEvent: SensorEvent) {
            Log.d(
                TAG,
                "onSensorChanged: X:  ${sensorEvent.values[0]}, Y:  ${sensorEvent.values[1] + sensorEvent.values[2]}"
            )
            x1 -= sensorEvent.values[0].toInt()
            y1 += sensorEvent.values[1].toInt()
            x2 -= (sensorEvent.values[0] * 2).toInt()
            y2 += (sensorEvent.values[1] * 2).toInt()
            x3 -= (sensorEvent.values[0] * 3).toInt()
            y3 += (sensorEvent.values[1] * 3).toInt()
        }

        override fun onAccuracyChanged(sensor: Sensor, i: Int) {}
    }

    override fun onDraw(canvas: Canvas) {
        val maxX = width
        val maxY = height
        super.onDraw(canvas)
        if (x1 + radius > maxX) {
            x1 = maxX - radius
        }
        if (y1 + radius > maxY) {
            y1 = maxY - radius
        }
        if (x1 < radius) {
            x1 = radius
        }
        if (y1 < radius) {
            y1 = radius
        }
        if (x2 + radius > maxX) {
            x2 = maxX - radius
        }
        if (y2 + radius > maxY) {
            y2 = maxY - radius
        }
        if (x2 < radius) {
            x2 = radius
        }
        if (y2 < radius) {
            y2 = radius
        }
        if (x3 + radius > maxX) {
            x3 = maxX - radius
        }
        if (y3 + radius > maxY) {
            y3 = maxY - radius
        }
        if (x3 < radius) {
            x3 = radius
        }
        if (y3 < radius) {
            y3 = radius
        }
        canvas.drawCircle(x1.toFloat(), y1.toFloat(), radius.toFloat(), paint[0])
        canvas.drawCircle(x2.toFloat(), y2.toFloat(), radius.toFloat(), paint[1])
        canvas.drawCircle(x3.toFloat(), y3.toFloat(), radius.toFloat(), paint[2])
        invalidate()
    }

    init {
        paint[0].color = context?.getColor(com.comma.uikit.R.color.blue_200)!!
        paint[1].color = context.getColor(com.comma.uikit.R.color.purple_200)
        paint[2].color = context.getColor(com.comma.uikit.R.color.yellow_200)
    }
}