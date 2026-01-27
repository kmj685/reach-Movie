package com.dapaeng12.ruachmovie

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MyViewHolder(itemView: View, recyclerviewInterface: MyRecyclerviewInterface): RecyclerView.ViewHolder(itemView), View.OnClickListener {
    val TAG: String = "로그"

    private val userNameTextView = itemView.findViewById<TextView>(R.id.user_name_text)
    private val profileImageView = itemView.findViewById<ImageView>(R.id.profile_img)
    private var myRecyclerviewInterface: MyRecyclerviewInterface? = null

    //기본 생서자
    init {
        Log.d(TAG, "MyViewHolder -  called")
        itemView.setOnClickListener(this)
        this.myRecyclerviewInterface = recyclerviewInterface
    }

    //데이터와 뷰를 묶는다.
    fun bind(myModel: MyModel){
        Log.d(TAG, "MyViewHolder - bind called")
        //텍스트(이름 부분 틀)
        userNameTextView.text = myModel.name
        //사진 부분 틀
        Glide
            .with(App.instance)
            .load(myModel.profileImage)
            .placeholder(R.mipmap.ic_launcher)
            .into(profileImageView)
    }

    override fun onClick(v: View?) {
        Log.d(TAG, "MyViewHolder - onClick called")

        this.myRecyclerviewInterface?.onItemClicked(adapterPosition)

    }
}