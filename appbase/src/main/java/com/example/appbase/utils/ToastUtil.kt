package com.example.appbase.utils

import android.widget.Toast
import com.example.appbase.config.BaseApplication

/**
 * FileName: ToastUtil
 * Founder: Jiang Houren
 * Create Date: 2022/8/23 11:29
 * Profile:
 */
object ToastUtil {

    /**
     * 短提示
     * @param message 提示语
     */
    fun shortShow(message: String) {
        Toast.makeText(
            BaseApplication.mContext,
            message, Toast.LENGTH_SHORT
        ).show()
    }

    /**
     * 长提示
     * @param message 提示语
     */
    fun longShow(message: String) {
        Toast.makeText(
            BaseApplication.mContext,
            message, Toast.LENGTH_LONG
        ).show()
    }

}