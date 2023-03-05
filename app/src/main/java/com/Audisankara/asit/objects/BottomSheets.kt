package com.Audisankara.asit.objects

import android.content.Context
import com.Audisankara.asit.R
import com.google.android.material.bottomsheet.BottomSheetDialog

object BottomSheets {

    //this fun returns the bottomsheet
    fun notAvailableBottomSheet(context : Context) : BottomSheetDialog {
        return BottomSheetDialog(context).also {
            it.setContentView(R.layout.not_available)
            it.show()
        }
    }

}