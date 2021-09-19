package com.example.weeknineapi.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weeknineapi.Model.PostCommentsResponseItem
import com.example.weeknineapi.Model.ResponseItem
import com.example.weeknineapi.R

class PostCommentAdapter:RecyclerView.Adapter<PostCommentAdapter.MyViewHolder>() {
    //var posts = ArrayList<ResponseItem>()
    var comments = ArrayList<PostCommentsResponseItem>()




    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val textViewBody = view.findViewById<TextView>(R.id.titles)
        val textViewTitle = view.findViewById<TextView>(R.id.postBody)
        val textViewUserId = view.findViewById<TextView>(R.id.postId)
      //  val postId = view.findViewById<TextView>(R.id.tvPost)
      //  val postTittle = view.findViewById<TextView>(R.id.tvTittle)


        fun bindIt(data: PostCommentsResponseItem) {
            textViewBody.text = data.body
            textViewTitle.text = data.email
            textViewUserId.text = data.name

        }
//
//        fun bindView(data: ResponseItem) {
//            postId.text = data.id.toString()
//            postTittle.text = data.title
//        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.post_comment_list,parent,false)
        return MyViewHolder(inflater)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // holder.bindView(posts[position])
          holder.bindIt(comments[position])

    }



    override fun getItemCount(): Int {
    //    return posts.size
        return comments.size

    }
}