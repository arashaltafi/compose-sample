package ir.arash.altafi.sample.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CelebrityResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String,
) : Parcelable