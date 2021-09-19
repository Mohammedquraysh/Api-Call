package com.example.weeknineapi.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weeknineapi.API.AddPostDetails
import com.example.weeknineapi.API.RetroService
import com.example.weeknineapi.Model.ResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddPostViewModel: ViewModel() {

    // mutable live variable
    val addPostRecycler: MutableLiveData<ResponseItem> = MutableLiveData()

    // function  initializing liveData
    fun getAddedPost():MutableLiveData<ResponseItem>{
        return addPostRecycler
    }


    // function to make Api call for adding new post
    fun makeAddApiCall(user: ResponseItem){
        val addedPost =  AddPostDetails.getAddedPost().create(RetroService::class.java)
        val call  = addedPost.createUser(user)
        call.enqueue(object : Callback<ResponseItem>{
            override fun onResponse(call: Call<ResponseItem>, response: Response<ResponseItem>) {
                if (response.isSuccessful){
                    addPostRecycler.postValue(response.body())
                }else{

                }

            }

            override fun onFailure(call: Call<ResponseItem>, t: Throwable) {
                addPostRecycler.postValue(null)

            }

        })
    }
}