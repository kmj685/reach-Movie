package com.dapaeng12.ruachmovie

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.RadioGroup
import android.widget.ScrollView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dapaeng12.ruachmovie.model.Photo
import com.dapaeng12.ruachmovie.recyclerview.PhotoGridRecyclerViewAdaptor
import com.dapaeng12.ruachmovie.retrofit.RetrofitManager
import com.dapaeng12.ruachmovie.utils.Constants
import com.dapaeng12.ruachmovie.utils.RESPONSE_STATEUS
import com.dapaeng12.ruachmovie.utils.SEARCH_TYPE
import com.dapaeng12.ruachmovie.utils.onMyTextChanged
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    val TAG: String = "로그"

    //데이터를 담을 그릇 즉 배열
    var modelList = ArrayList<Photo>()

    private lateinit var radioGroup: RadioGroup
    private lateinit var searchTermTextLayout: TextInputLayout
    private lateinit var searchTermEditText: TextInputEditText
    private lateinit var frameSearchBtn: FrameLayout
    private lateinit var mainScrollView: ScrollView
    private lateinit var searchBtn : MaterialButton
    private lateinit var searchProgress : ProgressBar

    private lateinit var myRecyclerView: RecyclerView




    private var currentSearchType : SEARCH_TYPE = SEARCH_TYPE.PHOTO
    private lateinit var myRecyclerAdapter: PhotoGridRecyclerViewAdaptor


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_scrollview)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Log.d(Constants.TAG, "MainActivity - onCreate called")

        radioGroup = findViewById(R.id.search_term_radio_group)
        searchTermTextLayout = findViewById(R.id.search_term_text_layout)
        searchTermEditText = findViewById(R.id.search_term_edit_text)
        frameSearchBtn = findViewById(R.id.frame_search_btn)
        mainScrollView = findViewById(R.id.main_scrollview)
        searchBtn = findViewById(R.id.material_button)
        searchProgress = findViewById(R.id.btn_progress)

        myRecyclerView = findViewById(R.id.my_recycler_view)



        //라디오 그룹 가져오기
        radioGroup.setOnCheckedChangeListener { _, checkedBtn ->
            // switch 문
            when(checkedBtn){
                R.id.photo_search_radio_btn -> {
                    Log.d(TAG, "사진검색 버튼 클릭!")
    
                    searchTermTextLayout.hint = "사진검색"
                    searchTermTextLayout.startIconDrawable = resources.getDrawable(R.drawable.photo_library, resources.newTheme())
    
                    this.currentSearchType = SEARCH_TYPE.PHOTO
                }

                R.id.user_search_radio_btn -> {
                    Log.d(TAG, "사용자 검색 버튼 클릭!")
    
                    searchTermTextLayout.hint = "사용자 검색"
                    searchTermTextLayout.startIconDrawable = resources.getDrawable(R.drawable.outline_frame_person_24)

                    this.currentSearchType = SEARCH_TYPE.USER
                }
            }
            Log.d(TAG, "MainActivity - onCheckedChanged() called / currentSearchType : $currentSearchType")
        }

        // 텍스트가 변경이 되었을 때
        searchTermEditText.onMyTextChanged {
            if (it.toString().count() > 0){
                // 입력 된 글자가 하나라도 있으면 버튼을 보여준다
                frameSearchBtn.visibility = View.VISIBLE
                // 헬퍼 텍스트를 없앤다.
                searchTermTextLayout.helperText = null
                // 스크롤뷰를 올린다.
                mainScrollView.scrollTo(0, 200)

            } else {
                // 그렇지 않은 경우엔 검색 버튼 안보이게
                frameSearchBtn.visibility = View.INVISIBLE
            }
            if (it.toString().count() == 12){
                // 검색어 12자 넘어가면 스낵바 띄우기
                Snackbar.make(
                    mainScrollView,
                    "검색어는 12자 까지만 입력 가능합니다.",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }

        // 버튼 클릭 시 동작 설정
        searchBtn.setOnClickListener {
            Log.d(TAG, "MainActivity - 검색 버튼이 클릭되었습니다. / currentSearchType : $currentSearchType")

            this.handleSearchButtonUi()

            val userSearchInput = searchTermEditText.text.toString()

            //검색 api호출
            RetrofitManager.instance.searchPhotos(searchTerm = findViewById<TextInputEditText>(R.id.search_term_edit_text).text.toString(), completion = {
                    responseState, responseDataArrayList ->

                when(responseState){
                    RESPONSE_STATEUS.SUCCESS -> {
                        Log.d(TAG, "api 호출 성공 : ${responseDataArrayList?.size}")

                        //context에 this 대신 만들어두었던 MyApp.appContext가 들어가도 된다.
                        val intent = Intent(this, PhotoCollectionActivity::class.java)

                        val bundle = Bundle()

                        // 검색 결과를 설정하는 시리얼라이즈
                        bundle.putSerializable("photo_array_list", responseDataArrayList)

                        // 검색 결과
                        intent.putExtra("array_bundle", bundle)

                        // 검색어
                        intent.putExtra("search_term", userSearchInput)

                        // 이 액티비티로 시작한다는 뜻이다. compose navigate.push 와 합쳐진 것이다.
                        startActivity(intent)
                    }
                    RESPONSE_STATEUS.FAIL -> {
                        Toast.makeText(this, "api 호출 에러입니다.", Toast.LENGTH_SHORT).show()
                        Log.d(TAG, "api 호출 실패 : $responseDataArrayList")
                    }
                    RESPONSE_STATEUS.NO_CONTENT -> {
                        Toast.makeText(this, "검색 결과가 없습니다.", Toast.LENGTH_SHORT).show()
                        Log.d(TAG, "api 검색 결과 없음 : ${responseDataArrayList?.size}")
                    }
                }
                searchProgress.visibility = View.INVISIBLE
                searchBtn.text = "검색"
                searchTermEditText.text = null
            })
        }

        Log.d(TAG, "RecyclerView - onCreate called")
        Log.d(TAG, "RecyclerView - this.modelList.size: ${this.modelList.size}")


        // 어답터 인스턴스 생성
        myRecyclerAdapter = PhotoGridRecyclerViewAdaptor()

        myRecyclerAdapter.submitList(this.modelList)

        // 리사이클러뷰 설정
        myRecyclerView.apply {

            //리사이클러뷰 방향 등 설정
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            //어답터 장착
            adapter = myRecyclerAdapter
        }

    }//onCreate

    private fun handleSearchButtonUi(){

        searchProgress.visibility = View.VISIBLE

        searchBtn.text = null


//        Handler().postDelayed({
//            searchBtn.text = "검색"
//            searchProgress.visibility = View.INVISIBLE
//        }, 1500)
    }

}