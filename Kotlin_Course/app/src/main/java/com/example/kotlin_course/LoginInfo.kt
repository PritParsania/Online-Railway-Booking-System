package com.example.kotlin_course

class LoginInfo {
    companion object {
        var userEmail: String = "nilayparikh134@gmail.com"
        var userPassword: String = "12345"
        var userName: String = "Nilay Parikh"
        var userCity: String = "Patan,Gujarat"
        var userPhoneNo: String = "+91 8460642744"
        var userIsLogin: Boolean = false
        fun Login(email: String, pwd: String): Boolean {
            userIsLogin = userEmail == email && pwd == userPassword
            return userIsLogin
        }
    }
}