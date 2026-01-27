package com.dapaeng12.ruachmovie

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerVieww : AppCompatActivity(), MyRecyclerviewInterface {

    val TAG: String = "로그"

    //데이터를 담을 그릇 즉 배열
    var modelList = ArrayList<MyModel>()

    private lateinit var myRecyclerAdapter: MyRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recycler_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.my_recycler_view)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        Log.d(TAG, "RecyclerView - onCreate called")
        Log.d(TAG, "RecyclerView - this.modelList.size: ${this.modelList.size}")

        for (i in 1..10){
            val myModel = MyModel(name = "노영택 $i", profileImage = "https://yt3.ggpht.com/-uLq1lI1er_M8AM8ghMZnv6djtfif2kog6aOqXnO4D6SQpaI3oMJmb_OJwKsTVqwgiYnVEPPOs8=s48-c-k-c0x00ffffff-no-rj")
            this.modelList.add(myModel)
        }
        Log.d(TAG, "RecyclerView - 반복문 돌린 후 this.modelList.size: ${this.modelList.size}")

        // 어답터 인스턴스 생성
        myRecyclerAdapter = MyRecyclerAdapter(this)

        myRecyclerAdapter.submitList(this.modelList)
        // 리사이클러뷰 설정
        findViewById<RecyclerView>(R.id.my_recycler_view).apply {

            //리사이클러뷰 방향 등 설정
            layoutManager = LinearLayoutManager(this@RecyclerVieww, LinearLayoutManager.VERTICAL, false)
            //어답터 장착
            adapter = myRecyclerAdapter
        }
    }

    override fun onItemClicked(position: Int) {
        Log.d(TAG, "RecyclerVieww - onItemClicked called/ position: $position")
    }
}