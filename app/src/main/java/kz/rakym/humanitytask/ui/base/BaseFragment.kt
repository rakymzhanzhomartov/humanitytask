package kz.rakym.humanitytask.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import kz.rakym.humanitytask.MainActivity

open class BaseFragment: Fragment() {

    protected var mainActivity: MainActivity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (activity is MainActivity) {
            mainActivity = activity as MainActivity
        }
    }

    fun showLoading(){
        mainActivity?.showLoading()
    }

    fun hideLoading(){
        mainActivity?.hideLoading()
    }

    fun observeBaseViewState(viewState: BaseViewState){
        when(viewState){
            is BaseViewState.Loading -> {
                showLoading()
            }
            is BaseViewState.Error -> {
                hideLoading()
                showMessage(viewState.message)
            }
        }
    }

    fun showMessage(message: String?){
        message?.let {
            Snackbar.make(requireActivity().findViewById(android.R.id.content), it, Snackbar.LENGTH_LONG).show()
        }
    }
}