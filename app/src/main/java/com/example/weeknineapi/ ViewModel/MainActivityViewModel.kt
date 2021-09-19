package com.example.weeknineapi.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weeknineapi.API.PostRetroInstance
import com.example.weeknineapi.API.RetroInstance
import com.example.weeknineapi.API.RetroService
import com.example.weeknineapi.Model.PostCommentsResponseItem
import com.example.weeknineapi.Model.ResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel: ViewModel() {

    // mutable live variable
    var recyclerListData: MutableLiveData<List<ResponseItem>> = MutableLiveData()
    var recyclerData: MutableLiveData<List<PostCommentsResponseItem>> = MutableLiveData()
    var recycle: MutableLiveData<ResponseItem> = MutableLiveData()

    init {
        makeApiCall()

    }

    // function  initializing liveData
    fun getUserListObservable(): MutableLiveData<List<ResponseItem>> {
        return recyclerListData
    }


    // function to make Api call for getting posts
    fun makeApiCall() {
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.getUsersList()

        call.enqueue(object : Callback<List<ResponseItem>> {
            override fun onResponse(
                call: Call<List<ResponseItem>>,
                response: Response<List<ResponseItem>>
            ) {
                if (response.isSuccessful) {
                    recyclerListData.value = response.body()
        //            recyclerListData.postValue(response.body())
                    Log.d("MainViewModel", "Create: ${response.body()}")
                } else {

                }
            }

            override fun onFailure(call: Call<List<ResponseItem>>, t: Throwable) {
                recyclerListData.postValue(null)
               Log.d("MainViewModel", "Failed")
            }

        })

    }


    // function to make Api call for fetching post comments
    fun makeApiCalls(id: String){
        val postRetroDetails =  PostRetroInstance.getRetroDetails().create(RetroService::class.java)
        val call = postRetroDetails.getpostDetails(id)
        call.enqueue(object: Callback<List<PostCommentsResponseItem>>{
            override fun onResponse(
                call: Call<List<PostCommentsResponseItem>>,
                response: Response<List<PostCommentsResponseItem>>
            ) {
                if (response.isSuccessful){
                    recyclerData.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<PostCommentsResponseItem>>, t: Throwable) {
             recyclerData.postValue(null)

            }

        })
    }
//
//    fun  makeSecondApiCall(id: String){
//        val postIdRetroInstance = PostIdRetroInstance.getRetroPostDetails().create(RetroService::class.java)
//       val call = postIdRetroInstance.getPosts(id)
//        call.enqueue(object : Callback<ResponseItem>{
//            override fun onResponse(
//                call: Call<ResponseItem>,
//                response: Response<ResponseItem>
//            ) {
//                if (response.isSuccessful){
//                    recycle.value = response.body()
//                }
//            }
//
//            override fun onFailure(call: Call<ResponseItem>, t: Throwable) {
//                recycle.postValue(null)
//            }
//
//        })
//
//    }
}