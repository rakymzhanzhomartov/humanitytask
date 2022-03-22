package kz.rakym.humanitytask.ui.base

abstract class BaseViewState{

    object Loading : BaseViewState()
    class Error(val message: String?): BaseViewState()

}
