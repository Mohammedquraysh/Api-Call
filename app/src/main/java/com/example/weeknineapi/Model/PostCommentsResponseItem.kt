package com.example.weeknineapi.Model

// data class for comment
data class PostCommentsResponseItem(
    val body: String,
    val email: String,
    val id: Int,
    val name: String,
    val postId: Int
)