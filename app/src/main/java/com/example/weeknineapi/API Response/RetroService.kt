package com.example.weeknineapi.API

import com.example.weeknineapi.Model.PostCommentsResponseItem
import com.example.weeknineapi.Model.ResponseItem
import retrofit2.Call
import retrofit2.http.*


interface RetroService {


 // https://jsonplaceholder.typicode.com/posts/1

  //https://jsonplaceholder.typicode.com/posts/1/comments


  // to get the post from api
    @GET("posts")
    fun getUsersList(): Call<List<ResponseItem>>

    // to post to the Api
    @POST("posts")
    fun createUser(@Body params: ResponseItem ): Call<ResponseItem>

    //to search for post
    @GET("posts")
     fun searchUsers(@Query("")searchText:String): Call<List<ResponseItem>>

  // to get all the post comments
     @GET("posts/{id}/comments")
     fun getpostDetails(@Path("id") id: String): Call<List<PostCommentsResponseItem>>

     @GET("post/{id}")
     fun getPosts(@Path("id") id: String): Call<ResponseItem>



}