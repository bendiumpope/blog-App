package com.itex.blogapplication.data.network.responses

import com.itex.blogapplication.data.db.entities.User

data class AuthResponse(
  val isSuccessful:Boolean?,
  val message: String?,
  val user: User?
)