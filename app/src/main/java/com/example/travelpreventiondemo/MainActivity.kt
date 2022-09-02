package com.example.travelpreventiondemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.alibaba.android.arouter.launcher.ARouter
import com.example.appbase.config.ARouteConfig
import com.example.appbase.network.BaseApi
import com.example.appbase.ui.BaseActivity
import com.example.appbase.utils.ToastUtil
import com.example.travelpreventiondemo.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initClick()
        checkKey()
    }

    /**
     * 检测key值
     */
    private fun checkKey(){
        if (TextUtils.isEmpty(BaseApi.KEY)){
            ToastUtil.shortShow(getString(R.string.enter_key_please))
        }
    }

    /**
     * 监听事件
     */
    private fun initClick() {
        //查询核酸检测机构
        mViewBinding.llAgency.setOnClickListener {
            ARouter.getInstance().build(ARouteConfig.AGENCY_MESSAGE)
                //传递String类型的值
                .withString("a", "a")
                //传递布尔类型的值
                .withBoolean("b", false)
                //指定启动模式
                .withFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                .navigation()
        }
        //查询风险等级地区
        mViewBinding.llRiskLevel.setOnClickListener {
            ARouter.getInstance().build(ARouteConfig.RISK_LEVEL)
                .navigation()
        }
        //查询出行政策
        mViewBinding.llTravel.setOnClickListener {
            ARouter.getInstance().build(ARouteConfig.TRAVEL_POLICY)
                .navigation()
        }
        // Web 待开发
        mViewBinding.llWeb.setOnClickListener {
            ToastUtil.shortShow("待开发...")
        }
    }

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(
            layoutInflater
        )
    }
}