package com.dapaeng12.ruachmovie

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class MyRecyclerAdapter(myRecyclerviewInterface: MyRecyclerviewInterface): RecyclerView.Adapter<MyViewHolder>() {
    val TAG: String = "로그"
    private var modelList = ArrayList<MyModel>()

    private var myRecyclerviewInterface: MyRecyclerviewInterface? = null

    //생성자
    init{
        this.myRecyclerviewInterface = myRecyclerviewInterface
    }
    //뷰홀더가 생성 되었을 떄
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_recycler_view_item, parent, false), this.myRecyclerviewInterface!!)
    }
    // 뷰와 뷰홀더가 묶였을 때
    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        Log.d(TAG, "MyRecyclerAdapter - onBindViewHolder called / position: $position")
        holder.bind(this.modelList[position])
//        holder.itemView.setOnClickListener { Toast.makeText(App.instance, "클릭됨! ${this.modelList[position].name}", Toast.LENGTH_SHORT).show() }
    }
    // 목록의 아이템 수
    override fun getItemCount(): Int {
        return this.modelList.size
    }
    fun submitList(modelList: ArrayList<MyModel>){
        this.modelList = modelList
    }
}