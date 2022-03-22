package kz.rakym.humanitytask.ui.home

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kz.rakym.domain.entity.Weather
import kz.rakym.domain.entity.WeatherData
import kz.rakym.humanitytask.R
import kz.rakym.humanitytask.databinding.FragmentHomeBinding
import kz.rakym.humanitytask.ui.base.BaseFragment
import kz.rakym.humanitytask.ui.utils.BackgroundBitmapDrawable
import kz.rakym.humanitytask.ui.utils.onDestroyNullable
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class HomeFragment : BaseFragment() {

    private var binding: FragmentHomeBinding by onDestroyNullable()
    private val homeViewModel: HomeViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        handleState()
        setViews()
        setBackgroundImage(Weather.WeatherType.Clear)
        return binding.root
    }

    private fun setViews() {
        binding.apply {
            fabTemperatureSystem.setOnClickListener {
                TemperatureSelectDialogFragment().show(
                    childFragmentManager,
                    TemperatureSelectDialogFragment.TAG
                )
            }
            fabTemperatureSystem.text = homeViewModel.temperatureSystem.name

            swipeContainer.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
            )
            swipeContainer.setOnRefreshListener {
                homeViewModel.refresh()
                swipeContainer.isRefreshing = false
            }
        }
    }

    private fun handleState() {
        homeViewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is HomeViewState.Success -> {
                    hideLoading()
                    setWeatherData(it.data)
                }
                else -> observeBaseViewState(it)
            }
        }
    }

    private fun setWeatherData(data: WeatherData) {
        binding.apply {
            location.visibility = View.VISIBLE
            location.text = data.name

            weatherDescription.visibility = View.VISIBLE
            weatherDescription.text = data.weather.first().description

            weatherTemperature.visibility = View.VISIBLE
            weatherTemperature.text =
                data.main.temp.toString() + " " + homeViewModel.temperatureSystem.symbol

        }
        setBackgroundImage(data.weather.first().getWeatherType())
    }

    private fun setBackgroundImage(type: Weather.WeatherType) {
        val bgResource: Int = when (type) {
            Weather.WeatherType.Clouds -> R.drawable.weather_cloudy
            Weather.WeatherType.Rain -> R.drawable.weather_rain
            Weather.WeatherType.Fog -> R.drawable.weather_fog
            Weather.WeatherType.Snow -> R.drawable.weather_snow
            else -> R.drawable.weather_default
        }
        setBackground(bgResource)
    }

    private fun setBackground(drawableId: Int) {
        var bitmap = BitmapFactory.decodeResource(
            resources,
            drawableId
        )
        bitmap = Bitmap.createScaledBitmap(
            bitmap!!,
            Resources.getSystem().displayMetrics.widthPixels,
            Resources.getSystem().displayMetrics.heightPixels,
            true
        )
        val bitmapDrawable = BackgroundBitmapDrawable(resources, bitmap)

        binding.swipeContainer.background = bitmapDrawable
    }
}