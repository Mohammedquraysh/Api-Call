package com.example.weeknineapi.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weeknineapi.UI.ApiDetailsActivity
import com.example.weeknineapi.Model.ResponseItem
import com.example.weeknineapi.R

class RecyclerViewAdapter(var context: Context) : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
    var postlist = ArrayList<ResponseItem>()
   //
//    private lateinit var nListener: OnItemClickListener
//
//    interface OnItemClickListener{
//
//        fun onItemClick(position:Int,value: View?)
//    }
//
//    fun setOnItemClickListener(listener: OnItemClickListener){
//        nListener = listener
//    }


    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        //listener: OnItemClickListener

        val textViewBody= view.findViewById<TextView>(R.id.textViewBody)
        val textViewTitle = view.findViewById<TextView>(R.id.textViewTitle)
        val textViewUserId = view.findViewById<TextView>(R.id.textViewUserId)


        fun bind(data: ResponseItem){
            textViewBody.text = data.body
            textViewTitle.text = data.title
            textViewUserId.text = data.userId.toString()


        }

//        override fun onClick(p0: View?) {
//            val position = adapterPosition
//            nlistener.onItemClick(position)
//        }

//        init {
//            itemView.setOnClickListener {
//                listener.onItemClick(adapterPosition,itemView)
//            }
//        }

    }



//    interface OnItemClickListener{
//
//        fun onItemClick(position:Int)
//    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.recycler_row_list,parent,false)
        return MyViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(postlist[position])
        holder.itemView.setOnClickListener {
            val intent = Intent(context, ApiDetailsActivity::class.java)
            val id = position
            intent.putExtra("id",id)

            intent.putExtra("title",postlist[position].title)
          //  intent.putExtra("ids", postlist[position].id)
            // intent.putExtra("contact", contact[position])

            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
       return postlist.size
    }

//    fun setUpdatedData(post: ArrayList<Response>){
//        this.postlist = post
//        notifyDataSetChanged()
//
//    }


}