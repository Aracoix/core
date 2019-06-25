package com.ihomefnt.core

import android.os.Bundle
import com.ihomefnt.commonlib.base.BaseActivity
import com.ihomefnt.commonlib.http.http
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.await

class User

class MainActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        launch(Dispatchers.IO) {
            val api = Api.service.login("user", "pass")
            val api2 = Api.service.login("user", "pass")
            val await = api.await()
            val await2 = api2.await()
        }
        http<BaseResponse<User>> {
            api = Api.service.login("user", "pass")
            onSuccess {

            }
            onComplete {

            }
            onFailed{

            }
        }
    }
}
