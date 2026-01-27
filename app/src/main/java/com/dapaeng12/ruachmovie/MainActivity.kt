package com.dapaeng12.ruachmovie

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    val TAG: String = "로그"

    private lateinit var textView: TextView
    private lateinit var  login_button: Button

    // 액티비티가 생성되었을 떄
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // 레이아웃을 설정
        setContentView(R.layout.activity_login_layout)
//        textView = findViewById(R.id.text_view)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login_layout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Log.d(TAG, "MainActivity - onCreate called")

        // 로그인 버튼 뷰에 클릭 리스너를 설정하였다.
        login_button = findViewById(R.id.login_button)
//        login_button.setOnClickListener(View.OnClickListener{
//            onLoginButtonClicked()
//        })
        //람다식
        login_button.setOnClickListener { onLoginButtonClicked() }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "MainActivity - onStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "MainActivity - onResume called")
    }

    override fun onPause() {
        super.onPause()
//        textView.visibility = View.VISIBLE
//        textView.setText("onPause()")
        Log.d(TAG, "MainActivity - onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "MainActivity - onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "MainActivity - onDestroy called")
    }

    fun onLoginButtonClicked(){
        Log.d(TAG, "MainActivity - onLoginButtonClicked called")

        val intent = Intent(this, BackActivity::class.java)

        startActivity(intent)
    }
}