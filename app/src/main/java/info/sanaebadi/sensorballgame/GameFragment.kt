package info.sanaebadi.sensorballgame

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import info.sanaebadi.sensorballgame.databinding.FragmentGameBinding


private var _binding: FragmentGameBinding? = null
private val binding get() = _binding!!
var sensorManger: SensorManager? = null
var accelerometer: Sensor? = null
lateinit var graphicView: GraphicView

@AndroidEntryPoint
class GameFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sensorManger = requireContext().getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManger!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        graphicView = GraphicView(requireContext())
        binding.inner.addView(graphicView)

    }

    override fun onResume() {
        super.onResume()
        sensorManger!!.registerListener(
            graphicView.sensorEventListener,
            accelerometer,
            SensorManager.SENSOR_DELAY_GAME
        )
    }

    override fun onPause() {
        super.onPause()
        sensorManger!!.unregisterListener(graphicView.sensorEventListener, accelerometer)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}