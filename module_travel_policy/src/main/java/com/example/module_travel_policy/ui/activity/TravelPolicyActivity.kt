package com.example.module_travel_policy.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.appbase.config.ARouteConfig.CITY_DATA
import com.example.appbase.config.ARouteConfig.TRAVEL_POLICY
import com.example.appbase.config.ParametersConfig.CITY_ID
import com.example.appbase.config.ParametersConfig.CITY_NAME
import com.example.appbase.config.RequsetCodeConfig.REQUSET_CODE_SELECT_FROM_CITY
import com.example.appbase.config.RequsetCodeConfig.REQUSET_CODE_SELECT_TO_CITY
import com.example.appbase.config.ResultCodeConfig
import com.example.appbase.ui.BaseActivity
import com.example.appbase.utils.DialogLoadingUtils
import com.example.module_travel_policy.R
import com.example.module_travel_policy.databinding.ActivityTravelPolicyBinding
import com.example.module_travel_policy.viewmodel.TravelPolicyViewModel

/**
 * 出行政策Activity
 */
@Route(path = TRAVEL_POLICY)
class TravelPolicyActivity : BaseActivity<ActivityTravelPolicyBinding>() {
    /**
     * viewModel
     */
    private val travelPolicyViewModel by viewModels<TravelPolicyViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }
    /**
     * 初始化
     */
    private fun init() {
        mViewBinding.toolbar.setNavigationOnClickListener {
            finish()
        }
        //选择出发地
        mViewBinding.tvFromCity.setOnClickListener {
            ARouter.getInstance().build(CITY_DATA)
                .navigation(this, REQUSET_CODE_SELECT_FROM_CITY)
        }
        //选择目的地
        mViewBinding.tvToCity.setOnClickListener {
            ARouter.getInstance().build(CITY_DATA)
                .navigation(this, REQUSET_CODE_SELECT_TO_CITY)
        }
    }
    var fromCityId: String? = null
    var toCityId: String? = null
    /**
     * 查询数据
     */
    private fun loadData() {
        fromCityId?.let { fromCityId ->
            toCityId?.let { toCityId ->
                DialogLoadingUtils.showLoading(this, "请稍后")
                travelPolicyViewModel.queryTravelPolicy(fromCityId, toCityId)
                    .observe(this, Observer {
                        DialogLoadingUtils.cancel()
                        it?.let {
                            if (it.error_code == 0) {
                                mViewBinding.bean = it.result
                            }
                        }
                    })
            }
        }
    }
    override fun getViewBinding(): ActivityTravelPolicyBinding {
        return ActivityTravelPolicyBinding.inflate(layoutInflater)
    }

    /**
     * 在回调中接收选择的城市数据，当出发地和目的地都不为空时，调用接口查询健康出行政策。
     */
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUSET_CODE_SELECT_FROM_CITY -> {
                //选择出发地
                if (resultCode == ResultCodeConfig.RESULE_CODE_SELECT_CITY_SUCCESS) {
                    fromCityId = data?.getStringExtra(CITY_ID)
                    mViewBinding.tvFromCity.text = data?.getStringExtra(CITY_NAME)
                    loadData()
                }
            }
            REQUSET_CODE_SELECT_TO_CITY -> {
                //选择目的地
                if (resultCode == ResultCodeConfig.RESULE_CODE_SELECT_CITY_SUCCESS) {
                    toCityId = data?.getStringExtra(CITY_ID)
                    mViewBinding.tvToCity.text = data?.getStringExtra(CITY_NAME)
                    loadData()
                }
            }
        }
    }
}
