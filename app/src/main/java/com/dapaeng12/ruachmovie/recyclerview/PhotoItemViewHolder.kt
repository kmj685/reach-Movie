package com.dapaeng12.ruachmovie.recyclerview

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dapaeng12.ruachmovie.MyApp
import com.dapaeng12.ruachmovie.R
import com.dapaeng12.ruachmovie.databinding.LayoutPhotoItemBinding
import com.dapaeng12.ruachmovie.model.Photo
import com.dapaeng12.ruachmovie.utils.Constants.TAG

class PhotoItemViewHolder(binding: LayoutPhotoItemBinding) : RecyclerView.ViewHolder(binding.root){

    // 바인딩으로 뷰를 가져온다.
    private val photoImageView = binding.photoImage
    private val photoCreatedAtText = binding.createdAtText
    private val photoLikesCountText = binding.likesCountText

    //데이터와 뷰를 묶는다.
    fun bindWithView(photoItem: Photo){
        Log.d(TAG, "PhotoItemViewHolder - bindWithView called")

        photoCreatedAtText.text = photoItem.createdAt

        photoLikesCountText.text = photoItem.likesCount.toString()

        // 이미지를 설정한다.(글라이드 라이브러리 사용)
        //with은 앱 전역으로 사용하는 컨텍스트를 넣는다.
        Glide.with(MyApp.appContext)
            //무엇을 로드 할 것이냐? -> 전에 엔티티로 설정했던 thumbnail
            .load(photoItem.thumbnail)
            //만약에 로드가 안되면 어떤 것을 대신하여 넣을 것이냐?
            .placeholder(R.drawable.outline_add_photo_alternate_24)
            //보여주는 실제 화면
            .into(photoImageView)

    }

}