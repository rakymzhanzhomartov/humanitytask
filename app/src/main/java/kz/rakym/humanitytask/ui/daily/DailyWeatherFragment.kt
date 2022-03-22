package kz.rakym.humanitytask.ui.daily

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kz.rakym.humanitytask.databinding.FragmentDashboardBinding
import kz.rakym.humanitytask.ui.base.BaseFragment
import kz.rakym.humanitytask.ui.utils.onDestroyNullable
import org.koin.androidx.viewmodel.ext.android.viewModel

class DailyWeatherFragment : BaseFragment() {

    private var _binding: FragmentDashboardBinding by onDestroyNullable()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding

    private val dailyWeatherViewModel: DailyWeatherViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        dailyWeatherViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }
}