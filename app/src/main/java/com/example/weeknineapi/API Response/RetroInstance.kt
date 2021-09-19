package com.example.weeknineapi.API

import com.example.weeknineapi.BASE_URL
import com.example.weeknineapi.DETAILS_URL
import com.example.weeknineapi.POST_DETAILS_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// class to make the call request
class RetroInstance {
    companion object{
        fun getRetroInstance(): Retrofit {
            val logging = HttpLoggingInterceptor()
            logging.level = (HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
            client.addInterceptor(logging)
            return  Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

    }

}

// class to make the call request
class PostRetroInstance{
   // https://jsonplaceholder.typicode.com/posts/1/comments
    companion object{

        fun getRetroDetails(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(DETAILS_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        }
    }
}


class PostIdRetroInstance{
    companion object{

        fun getRetroPostDetails(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(POST_DETAILS_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

    }
}


// class to make the call request
class AddPostDetails{
    companion object{
        fun getAddedPost(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}