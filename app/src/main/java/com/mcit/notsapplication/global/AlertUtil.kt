package com.mcit.notsapplication.global

import android.app.Activity
import android.view.Gravity
import com.mcit.notsapplication.R
import com.tapadoo.alerter.Alerter

object AlertUtil {

    fun showError(activity: Activity,item:Int, message: String) {
        if (activity.isFinishing || activity.isDestroyed) return
        Alerter.create(activity)
            .setTitle(activity.getString(R.string.error_title))
            .setText(message)
            .setTitleAppearance(R.style.AlertTextAppearance_Title)
            .setTextAppearance(R.style.AlertTextAppearance_Text)
            .setIconColorFilter(activity.resources.getColor(R.color.colorDarkGray))
            .setBackgroundColorRes(R.color.alertWarning) // or setBackgroundColorInt(Color.CYAN)
            .enableSwipeToDismiss()
            .setDuration(2000)
            .setContentGravity(Gravity.START)
            .show()
    }


    fun showSuccess(
        activity: Activity,

        message: String
    ) {
        if (activity.isFinishing || activity.isDestroyed) return
        Alerter.create(activity)

            .setText(message)
            .setTitleAppearance(R.style.AlertTextAppearance_Title_Success)
            .setTextAppearance(R.style.AlertTextAppearance_Text_Success)
            .setIconColorFilter(activity.resources.getColor(R.color.colorWhite))
            .setBackgroundColorRes(R.color.alerter_default_success_background) // or setBackgroundColorInt(Color.CYAN)
            .enableSwipeToDismiss()
            .setDuration(500)
            .setContentGravity(Gravity.START)
            .show()
    }
}