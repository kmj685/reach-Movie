package com.dapaeng12.ruachmovie

import android.os.Bundle
import android.util.Log
import android.widget.GridLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.dapaeng12.ruachmovie.databinding.ActivityPhotoCollectionBinding
import com.dapaeng12.ruachmovie.databinding.LayoutPhotoItemBinding
import com.dapaeng12.ruachmovie.model.Photo
import com.dapaeng12.ruachmovie.recyclerview.PhotoGridRecyclerViewAdaptor
import com.dapaeng12.ruachmovie.utils.Constants.TAG

class PhotoCollectionActivity: AppCompatActivity() {

    // 데이터
    private var photoList = ArrayList<Photo>()

    // 어답터
    private lateinit var photoGideRecyclerViewAdaptor: PhotoGridRecyclerViewAdaptor

    private lateinit var binding: ActivityPhotoCollectionBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotoCollectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 상태바 safe area 처리
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )
            insets
        }
        
        Log.d(TAG, "PhotoCollectionActivity - onCreate called")

        val bundle = intent.getBundleExtra("array_bundle")

        val searchTerm = intent.getStringExtra("search_term")

        photoList = bundle?.getSerializable("photo_array_list") as ArrayList<Photo>

        Log.d(TAG, "PhotoCollectionActivity - onCreate called / searchTerm : $searchTerm, photoList.count(): ${photoList.count()}")

        binding.topAppBar.title = searchTerm

        this.photoGideRecyclerViewAdaptor = PhotoGridRecyclerViewAdaptor()

        this.photoGideRecyclerViewAdaptor.submitList(photoList)

        //spanCount는 가로의 칸 수이다. orientation은 방향을 설정하는 것이다. reverseLayout은 데이터의 정보를 최신순인지 역방향인지 설정해주는 것이다.
        binding.myPhotoRecyclerView.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        binding.myPhotoRecyclerView.adapter = this.photoGideRecyclerViewAdaptor
    }
}