package com.example.weeknineapi.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weeknineapi.Model.ResponseItem
import com.example.weeknineapi.R
import com.example.weeknineapi.ViewModel.AddPostViewModel

class PostingActivity : AppCompatActivity() {
    private lateinit var viewModel: AddPostViewModel
    private lateinit var createTitle: EditText
    private lateinit var createEmail: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posting)
        var createButton = findViewById<Button>(R.id.createButton)
        initViewModel()
        createNewUserObservable()

        // button to create new post
        createButton.setOnClickListener {
            createNewPost()
        }
    }

    // function to create new post
    private fun createNewPost(){
         createTitle = findViewById(R.id.editTextname)
         createEmail = findViewById<EditText>(R.id.editTextEmail)
        var user = ResponseItem(createTitle.text.toString(),0,createEmail.text.toString(),0)
        viewModel.makeAddApiCall(user)
    }

    // function to initializing viewModel variable
    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(AddPostViewModel::class.java)

    }


    // this is the function to observe the call
   private fun createNewUserObservable(){
     viewModel.getAddedPost().observe(this, Observer {
         Log.d("PostingActivity", "Create: $it")
        if (it == null){
            Toast.makeText(this, "failed to create new user", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this, "created new user", Toast.LENGTH_LONG).show()

            val intent = Intent(this, MainActivity::class.java)
           // var users
            intent.putExtra("AddedPost", it)
            startActivity(intent)


        }
    })
}

}