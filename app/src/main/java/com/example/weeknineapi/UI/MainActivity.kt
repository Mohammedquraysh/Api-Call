package com.example.weeknineapi.UI

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weeknineapi.Adapter.RecyclerViewAdapter
import com.example.weeknineapi.Model.ResponseItem
import com.example.weeknineapi.UI.PostingActivity
import com.example.weeknineapi.R
import com.example.weeknineapi.ViewModel.MainActivityViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity: AppCompatActivity() {
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    var newPost: ResponseItem? = null
    private lateinit var myRecycler: RecyclerView
    lateinit var searchButton:  Button
    lateinit var searchText: EditText
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var floatButton: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // this help in passing the post from PostingActivity
        newPost = intent.getParcelableExtra("AddedPost")

      // initializing the views
        searchText = findViewById(R.id.searchBox)
        searchButton = findViewById(R.id.search_button)
        floatButton = findViewById(R.id.fltbtn)

        // button for searching new post
        searchButton.setOnClickListener {
            searchPost(searchText.text.toString())
        }

        // initializing the recyclerview
        myRecycler = findViewById(R.id.recyclerview)
        initRecyclerview()
        initViewModel()




        // this is the button to create new post
        floatButton.setOnClickListener {
            Intent(this, PostingActivity::class.java).also {
                startActivity(it)
            }
        }

    }




    // function to initializing recyclerview
    private fun initRecyclerview() {
        myRecycler.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration =
                DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            recyclerViewAdapter = RecyclerViewAdapter(this@MainActivity)
            adapter = recyclerViewAdapter
//           recyclerViewAdapter.setOnItemClickListener(object: RecyclerViewAdapter.OnItemClickListener{
//               override fun onItemClick(position: Int, value: View?) {
//                  val intent = Intent(this@MainActivity, ApiDetailsActivity::class.java)
//                   val id = position
//                   intent.putExtra("id",id)
//
//                   intent.putExtra("title",postList[position].title)
//                   intent.putExtra("id", postList[position].id)
//                 //  intent.putExtra("contact", contact[position])
//
//                   startActivity(intent)
//               }
//
//           })
        }
    }



    // this is the function to search for a particular pos
    @SuppressLint("NotifyDataSetChanged")
    fun searchPost(text: String){
        recyclerViewAdapter.postlist = recyclerViewAdapter.postlist.filter {
            it.title.contains(text, ignoreCase = false)
        } as ArrayList<ResponseItem>
        recyclerViewAdapter.notifyDataSetChanged()
    }



    // this is the function to observe the call
     @SuppressLint("NotifyDataSetChanged")
     fun initViewModel(){
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getUserListObservable().observe(this, Observer {
                Log.d("MainActivity", "Created: ${it}")
            if (it == null){
                Toast.makeText(this, "no result found", Toast.LENGTH_LONG).show()
            }else{
                recyclerViewAdapter.postlist = it as ArrayList<ResponseItem>

                if(newPost != null) {
                    recyclerViewAdapter.postlist.add(newPost!!)
                    Log.d("POST", "getPost: $newPost")
                }
                recyclerViewAdapter.notifyDataSetChanged()


                Log.d("MainActivity", "Created: $it")
            }
        })
    }

}