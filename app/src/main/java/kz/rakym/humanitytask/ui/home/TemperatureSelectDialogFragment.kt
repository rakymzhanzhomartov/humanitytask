package kz.rakym.humanitytask.ui.home

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import kz.rakym.domain.entity.TemperatureSystem
import kz.rakym.humanitytask.R
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class TemperatureSelectDialogFragment: DialogFragment() {


    private val homeViewModel: HomeViewModel by sharedViewModel()

    private val temperatureOptions: Array<CharSequence> = TemperatureOptions.values().map { it.name }.toTypedArray()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.choose_temperature_system))
            .setItems(temperatureOptions
            ) { dInterface, position ->
                homeViewModel.temperatureSystem = (TemperatureOptions.values()[position].getTemperatureSystem())
                dInterface.dismiss()
            }.create()

    enum class TemperatureOptions{
        CELSIUS,
        FAHRENHEIT;

        fun getTemperatureSystem(): TemperatureSystem {
            return when(this){
                CELSIUS -> TemperatureSystem.CELSIUS
                FAHRENHEIT -> TemperatureSystem.FAHRENHEIT
                else -> TemperatureSystem.DEFAULT
            }
        }
    }
    companion object{
        const val TAG = "TemperatureSelectDialogFragment"
    }
}