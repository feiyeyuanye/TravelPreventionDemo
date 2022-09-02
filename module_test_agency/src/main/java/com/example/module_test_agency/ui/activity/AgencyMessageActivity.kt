package com.example.module_test_agency.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.appbase.config.ARouteConfig.AGENCY_MESSAGE
import com.example.appbase.config.ARouteConfig.CITY_DATA
import com.example.appbase.config.ParametersConfig
import com.example.appbase.config.RequsetCodeConfig.REQUSET_CODE_SELECT_CITY
import com.example.appbase.config.ResultCodeConfig
import com.example.appbase.ui.BaseActivity
import com.example.appbase.utils.DialogLoadingUtils
import com.example.module_test_agency.R
import com.example.module_test_agency.databinding.ActivityAgencyMessageBinding
import com.example.module_test_agency.ui.adapter.AgencyMessageAdapter
import com.example.module_test_agency.viewmodel.AgencyViewModel


/**
 * 检测机构信息
 */
@Route(path = AGENCY_MESSAGE)
class AgencyMessageActivity : BaseActivity<ActivityAgencyMessageBinding>() {
    /**
     * viewModel
     */
    private val agencyViewModel by viewModels<AgencyViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }
    /**
     * 初始化
     */
    private fun init() {
        initView()
    }
    /**
     * 初始化view
     */
    private fun initView() {
        mViewBinding.toolbar.setOnClickListener {
            finish()
        }
        //选择城市监听事件
        mViewBinding.tvCity.setOnClickListener {
            // 路由到选择城市页面，并在 onActivityResult 中接收返回的数据
            ARouter.getInstance().build(CITY_DATA)
                .navigation(this, REQUSET_CODE_SELECT_CITY)
        }
        val layoutManager = LinearLayoutManager(this)
        mViewBinding.rvData.layoutManager = layoutManager
    }

    /**
     * 加载数据
     */
    private fun loadData(cityId: String) {
        DialogLoadingUtils.showLoading(this, getString(R.string.wait_please))
        agencyViewModel.loadTestAgencyMessage(cityId).observe(this) {
            DialogLoadingUtils.cancel()
            it?.let {
                Log.d("TAG","error_code: ${it.error_code}")
                if (it.error_code == 0) {
                    Log.d("TAG","请求成功")
                    it.result?.data?.let { data ->
                        val agencyMessageAdpter = AgencyMessageAdapter(data,
                            callback = { position, data ->
                                //以后可以在这里做扩展 如拨打电话、导航、自动定位当前城市等
                            })
                        mViewBinding.rvData.adapter = agencyMessageAdpter
                    }
                }
            }
        }
    }
    override fun getViewBinding(): ActivityAgencyMessageBinding {
        return ActivityAgencyMessageBinding.inflate(layoutInflater)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUSET_CODE_SELECT_CITY -> {
                Log.d("TAG","AgencyMessageActivity resultCode:$resultCode")
                when (resultCode) {
                    ResultCodeConfig.RESULE_CODE_SELECT_CITY_SUCCESS -> {
                        val cityId = data?.getStringExtra(ParametersConfig.CITY_ID)
                        val cityName = data?.getStringExtra(ParametersConfig.CITY_NAME)
                        Log.d("TAG","AgencyMessageActivity cityId:$cityId cityName:$cityName")
                        mViewBinding.tvCity.text = cityName
                        cityId?.let {
                            // 接收到选择的城市数据后，调用 loadData 方法查询检测机构信息
                            loadData(it)
                        }
                    }
                }
            }
        }
    }
}