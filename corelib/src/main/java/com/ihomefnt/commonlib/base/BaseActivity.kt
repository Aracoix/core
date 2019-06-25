package com.ihomefnt.commonlib.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ihomefnt.commonlib.permission.PermissionManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext


open class BaseActivity : AppCompatActivity(),CoroutineScope {

    private lateinit var job: Job // 定义job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()
    }

    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        PermissionManager.manager.handleResult(this,requestCode,permissions,grantResults)
    }
}
