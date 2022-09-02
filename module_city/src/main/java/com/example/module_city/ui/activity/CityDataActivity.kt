package com.example.module_city.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.appbase.config.ARouteConfig.CITY_DATA
import com.example.appbase.config.BaseApplication
import com.example.appbase.ui.BaseActivity
import com.example.module_city.R
import com.example.module_city.databinding.ActivityCityDataBinding
import com.example.module_city.ui.view.IndexView
import com.example.appbase.config.ParametersConfig
import com.example.appbase.config.ResultCodeConfig
import com.example.appbase.utils.DialogLoadingUtils
import com.example.module_city.bean.City
import com.example.module_city.bean.CityDataBean
import com.example.module_city.ui.adapter.CityDataAdapter
import com.example.module_city.viewmodel.CityDataViewModel

@Route(path = CITY_DATA)
class CityDataActivity : BaseActivity<ActivityCityDataBinding>() {

    /**
     * viewModel
     */
    private val cityDataViewModel by viewModels<CityDataViewModel>()

    /**
     * 城市数据
     */
    val cityList = mutableListOf<City>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    /**
     * 初始化
     */
    private fun init() {
        initView()
        loadData()
    }

    /**
     * 初始化view
     */
    private fun initView() {
        mViewBinding.toolbar.setOnClickListener {
            finish()
        }
        //索引滚动
        // IndexView 是一个自定义的字母索引滚动组件，当选择的字母改变时会回调 onIndexChange，并将 RecycleView 滚动到选中字母的首个数据项上。
        mViewBinding.indexView.setOnIndexChangeListener(object : IndexView.OnIndexChangeListener {
            override fun onIndexChange(word: String?) {
                mViewBinding.tvIndex.visibility = View.VISIBLE
                mViewBinding.tvIndex.text = word
                word?.let {
                    cityList.let { it ->
                        it.forEachIndexed { position, data ->
                            if (word == data.firstLetter) {
                                mViewBinding.rvData.scrollToPosition(position)
                                return
                            }
                        }
                    }
                }
            }

            override fun uplift() {
                mViewBinding.tvIndex.visibility = View.GONE
            }

        })
        val layoutManager = LinearLayoutManager(this)
        mViewBinding.rvData.layoutManager = layoutManager

    }

    /**
     * 加载数据
     */
    private fun loadData() {
        DialogLoadingUtils.showLoading(this, getString(R.string.wait_please))
        // 通过 ViewModel 获取数据并设置给 RecycleView
        cityDataViewModel.loadCityData().observe(this) {
            DialogLoadingUtils.cancel()
            it?.let {
                if (it.error_code == 0) {
                    //请求成功
                    it.result?.let { list ->
                        for (i in list.indices) {
                            val data = list[i].citys
                            data?.let { data ->
                                for (index in data.indices) {
                                    cityList.add(data[index])
                                }
                            }
                        }
                        cityList.sort()
                        val cityDataAdpter = CityDataAdapter(cityList,
                            // callback 高阶函数中，会将选择的城市名称、城市 id 返回到上一个页面。
                            callback = { position, data ->
                                Log.d("TAG","CityDataActivity 选择数据回调")
                                val intent = Intent()
                                intent.putExtra(ParametersConfig.CITY_ID, data.city_id)
                                intent.putExtra(ParametersConfig.CITY_NAME, data.city)
                                setResult(
                                    ResultCodeConfig.RESULE_CODE_SELECT_CITY_SUCCESS,
                                    intent
                                )
                                finish()
                            })
                        mViewBinding.rvData.adapter = cityDataAdpter

                    }
                }else if (it.error_code == 10012){
                    // {"resultcode":"112","reason":"超过每日可允许请求次数","result":null,"error_code":10012}
                    Toast.makeText(
                        BaseApplication.mContext,
                        BaseApplication.mContext.getString(com.example.appbase.R.string.exceed_allow_number), Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun getViewBinding(): ActivityCityDataBinding {
        return ActivityCityDataBinding.inflate(layoutInflater)
    }
}
