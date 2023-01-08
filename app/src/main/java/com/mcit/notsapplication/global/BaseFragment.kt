package com.mcit.notsapplication.global

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.mcit.notsapplication.R
import com.mcit.notsapplication.ui.activity.MainActivity
import java.util.*


abstract class BaseFragment : Fragment(){

    lateinit var parent: MainActivity

    abstract fun layoutResource(inflater: LayoutInflater, container: ViewGroup?): View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutResource(inflater, container)
    }


    fun showFailedMessage(message: String) {
        AlertUtil.showError(
            requireActivity(),
            R.string.error_title,
            message
        )
    }
    fun showSuccessMessage(titleMessage: String) {
        AlertUtil.showSuccess(
            requireActivity(),
            titleMessage
        )
    }
}