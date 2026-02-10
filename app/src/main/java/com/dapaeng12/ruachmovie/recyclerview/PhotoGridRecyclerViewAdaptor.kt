package com.dapaeng12.ruachmovie.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dapaeng12.ruachmovie.R
import com.dapaeng12.ruachmovie.databinding.LayoutPhotoItemBinding
import com.dapaeng12.ruachmovie.model.Photo

// 이렇게 매개변수로 어레이리스트를 넘겨도 된다
//class PhotoGridRecyclerViewAdaptor(photoList: ArrayList<Photo>) : RecyclerView.Adapter<PhotoItemViewHolder>(){

class PhotoGridRecyclerViewAdaptor() : RecyclerView.Adapter<PhotoItemViewHolder>(){

    private var photoList = ArrayList<Photo>()

    // 뷰 홀더가 만들어졌을 때(시작할 때) 뷰홀더와 레이아웃을 연결
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotoItemViewHolder {
        // 뷰바인딩을 썼기 때문에 LayoutPhotoItemBinding(바인딩으로 바꾼 것으로 아까 만들었던 layout_photo_item이다.)
        // parent.context 대신 만들어두었던 MyApp.AppContext를 가져와도 무방하다.
        return PhotoItemViewHolder(LayoutPhotoItemBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        ))
    }

    // 뷰가 묶였을 때 데이터를 뷰홀더에 넘겨준다.
    //PhotoItemViewHolder에서 만들었던 bindWithView를 호출하면 된다.
    override fun onBindViewHolder(
        holder: PhotoItemViewHolder,
        position: Int
    ) {
        // 이것의 포토리스트에 [position] 인덱스와 같은 의미이다. 를 넣으면 되는거다.
        holder.bindWithView(this.photoList[position])
    }

    // 보여줄 목록의 갯수
    override fun getItemCount(): Int {
        return this.photoList.size
    }

    // 외부에 어답터 데이터 배열을 넣어준다.
    fun submitList(photoList: ArrayList<Photo>){
        this.photoList = photoList
    }

}