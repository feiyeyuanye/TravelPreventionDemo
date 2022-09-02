package com.example.appbase.utils

import android.annotation.SuppressLint
import android.content.Context
import com.example.appbase.expand.defaultDialogView
import com.example.appbase.tools.LoadingDialog

/**
 * @author：HuangLinqing
 * @blog：https://huanglinqing.blog.csdn.net/?t=1
 * @公众号：Android 技术圈
 * @desc：加载对话框工具类
 */
object DialogLoadingUtils {

    @SuppressLint("StaticFieldLeak")
    private var dialog: LoadingDialog? = null

    /**
     * 显示等待框
     */
    fun showLoading(context: Context, mes: String) {

        dialog?.apply {
            if (isShowing) {
                cancel()
            }
        }

        dialog = LoadingDialog.Builder(context)
            .defaultDialogView()
            .setBootomDesc(mes)
            .create()
        dialog!!.show()
    }


    /**
     * 取消
     */
    fun cancel() {
        dialog?.cancel()
    }
}