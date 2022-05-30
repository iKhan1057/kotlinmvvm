package com.kotlinmvvm.data

import com.google.gson.annotations.SerializedName

data class CelebsModel(
    @SerializedName("name")
    var name:String?,
    @SerializedName("photo")
    var photo:String?,
    @SerializedName("gender")
    var gender:String?)