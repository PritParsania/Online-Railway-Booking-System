package com.example.kotlin_course

class userdata (
    val uid: String?,
    val ename: String?,
    val eemail: String,
    val enumber: String,
    val epassword: String
)
{
    constructor():this("","","","","")
}