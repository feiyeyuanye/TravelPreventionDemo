// 版本号管理
object Versions{
    const val compileSdkVersion = 32
    const val minSdkVersion = 23
    const val targetVersion = 32
    const val hilt_ktx_version = "2.42"
    const val hilt_compiler_ktx_version = "2.42"
    const val lifecycle_version = "2.3.1"
    const val fragment_ktx_version = "1.3.6"
    const val coil_version = "1.1.1"
    const val retrofit_version = "2.9.0"
    const val logging_interceptor_version = "4.7.2"
    const val coroutines_version = "1.4.1"
    const val arouter_api_version = "1.5.2"
    const val arouter_compiler_version = "1.5.2"
}

// 三方库管理
object Libs{
    const val hilt_ktx = "com.google.dagger:hilt-android:${Versions.hilt_ktx_version}"
    const val hilt_compiler_ktx = "com.google.dagger:hilt-android-compiler:${Versions.hilt_compiler_ktx_version}"
    const val livedata_ktx =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle_version}"
    const val viewmodel_ktx =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle_version}"
    const val viewmodel =
        "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifecycle_version}"
    const val fragment_ktx = "androidx.fragment:fragment-ktx:${Versions.fragment_ktx_version}"
    const val coil = "io.coil-kt:coil:${Versions.coil_version}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit_version}"
    const val converter_gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit_version}"
    const val logging_interceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.logging_interceptor_version}"
    const val coroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines_version}"
    const val coroutines_android =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines_version}"
    const val arouter_api = "com.alibaba:arouter-api:${Versions.arouter_api_version}"
    //    需要使用【kapt】引入注解处理器
    const val arouter_compiler = "com.alibaba:arouter-compiler:${Versions.arouter_compiler_version}"
}