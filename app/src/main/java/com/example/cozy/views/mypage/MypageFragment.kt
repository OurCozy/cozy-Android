package com.example.cozy.views.mypage

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.cozy.R
import com.example.cozy.network.RequestToServer
import com.example.cozy.network.customEnqueue
import com.example.cozy.network.responseData.RecentlySeenData
import com.example.cozy.views.search.SearchActivity
import kotlinx.android.synthetic.main.fragment_mypage.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class MypageFragment : Fragment() {
    val IMAGE_FROM_GALLERY = 0
    lateinit var adapter: RecentlySeenAdapter
    val service = RequestToServer.service
    lateinit var selectedImg : Uri
    var data = mutableListOf<RecentlySeenData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mypage, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = context!!.getSharedPreferences("TOKEN", Context.MODE_PRIVATE)
        val token = sharedPref.getString("token", "token")

        val header = mutableMapOf<String, String?>()
        header["Content-Type"] = "application/json"
        header["token"] = token

        //사용자 정보 불러오기
        service.requestUserInfo(header).customEnqueue(
            onError = { Log.d("response", "error")},
            onSuccess = {
                if(it.success) {
                    Log.d("response", it.message)
                    view.findViewById<TextView>(R.id.tv_user_name).text = it.data[0].nickname
                    tv_user_email.text = it.data[0].email
                    Glide.with(view).load(it.data[0].profile).into(rounded_iv_profile)
                }
            }
        )

        //검색창 열기
        activity!!.findViewById<ImageView>(R.id.iv_search).setOnClickListener {
            val intent = Intent(activity, SearchActivity::class.java)
            startActivity(intent)
        }

        rounded_iv_profile.setOnClickListener{
            startActivityForResult(Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), IMAGE_FROM_GALLERY)
        }

        adapter = RecentlySeenAdapter(view.context)
        rv_recently_seen.adapter = adapter

        loadData()
    }

    override fun onResume() {
        super.onResume()

        loadData()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //갤러리에서 선택한 사진 화면에 출력
        if(requestCode==IMAGE_FROM_GALLERY && resultCode==RESULT_OK && data!=null) {

            selectedImg = data!!.data!!
            val bitmap = MediaStore.Images.Media.getBitmap(context!!.contentResolver, selectedImg)
            Log.d("Test", selectedImg.toString() +"   " + bitmap)
            rounded_iv_profile.setImageBitmap(bitmap)
            val c = context!!.contentResolver.query(Uri.parse(selectedImg.toString()), null, null, null, null)
            c!!.moveToNext()
            val absolutePath = c!!.getString(c!!.getColumnIndex(MediaStore.MediaColumns.DATA))
            val file = File(absolutePath)
            val header = mutableMapOf<String, String?>()
            val sharedPref = context!!.getSharedPreferences("TOKEN", Context.MODE_PRIVATE)
            header["token"] = sharedPref.getString("token", "token")
            val rqFile = RequestBody.create(MediaType.parse("image/jpeg"), file)
            var photo : MultipartBody.Part = MultipartBody.Part.createFormData("profile", file.getName(), rqFile)
            service.requestUserProfile(photo,header).customEnqueue(
                onError = {Log.d("error >>>> ", "통신에러")},
                onSuccess = {
                    if (it.success){
                        Log.d("성공", it.message + " / ")
                    }else{
                        Log.d("status >>>> ", it.success.toString() + " / " + it.status.toString() + " / " + it.message)
                    }
                }
            )
            Log.d("이미지", selectedImg.toString())
        } else return
    }

    fun loadData() {
        data.apply {
            add(
                RecentlySeenData(
                    bookstoreName = "Piece",
                    image1 = "https://sopt-server-gain.s3.ap-northeast-2.amazonaws.com/1594260881260.jpg"
                )
            )
            add(
                RecentlySeenData(
                    bookstoreName = "서아책방",
                    image1 = "https://sopt-server-gain.s3.ap-northeast-2.amazonaws.com/1594262189420.jpg"
                )
            )
            add(
                RecentlySeenData(
                    bookstoreName = "가가77페이지",
                    image1 = "https://sopt-server-gain.s3.ap-northeast-2.amazonaws.com/1594262327987.png"
                )
            )
            add(
                RecentlySeenData(
                    bookstoreName = "지구불시착",
                    image1 = "https://sopt-server-gain.s3.ap-northeast-2.amazonaws.com/1594262285974.png"
                )
            )
            add(
                RecentlySeenData(
                    bookstoreName = "아크앤북",
                    image1 = "https://sopt-server-gain.s3.ap-northeast-2.amazonaws.com/1594262587468.png"
                )
            )
        }

        tv_no_recently_seen_background.visibility = View.GONE
        tv_no_recently_seen_text.visibility = View.GONE

        adapter.data = data
        adapter.notifyDataSetChanged()
    }
}
