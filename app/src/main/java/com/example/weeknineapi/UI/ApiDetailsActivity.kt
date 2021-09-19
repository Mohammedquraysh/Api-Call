package com.example.weeknineapi.UI

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weeknineapi.Adapter.PostCommentAdapter
import com.example.weeknineapi.Model.PostCommentsResponseItem
import com.example.weeknineapi.R
import com.example.weeknineapi.ViewModel.MainActivityViewModel

class ApiDetailsActivity : AppCompatActivity() {
    private lateinit var recyclerViews: PostCommentAdapter
    private lateinit var myRecycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_api_details)

        val postId = findViewById<TextView>(R.id.tvPost)


        // initializing the controller class
        myRecycler = findViewById(R.id.recycler)
        val newId = intent.getIntExtra("id", 1)
        postId.text = intent.getStringExtra("title")



        val id = newId + 1
        Toast.makeText(this, "$id", Toast.LENGTH_SHORT).show()
        initRecyclerview()
        makeApiCall(id.toString())


    }


    // initializing the recyclerview
    private fun initRecyclerview() {
        myRecycler.apply {
            layoutManager = LinearLayoutManager(this@ApiDetailsActivity)
            val decoration =
                DividerItemDecoration(this@ApiDetailsActivity, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            recyclerViews = PostCommentAdapter()
            adapter = recyclerViews
        }
    }




    // function to observe the call
    @SuppressLint("NotifyDataSetChanged")
    private fun makeApiCall(id: String){
        val viewModelTwo = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModelTwo.recyclerData.observe(this,{

            if (it != null){
                //initRecyclerview()
                Log.d("ApiDetailsActivity", "show $it")
                recyclerViews.comments = it as ArrayList<PostCommentsResponseItem>
                recyclerViews.notifyDataSetChanged()
                Toast.makeText(this,"Successful", Toast.LENGTH_SHORT).show()

            }else{
                Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show()
                Log.d("ApiDetailsActivity", "show $it")
            }

        })
        viewModelTwo.makeApiCalls(id)

    }

}