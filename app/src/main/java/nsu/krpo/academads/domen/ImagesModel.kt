package nsu.krpo.academads.domen

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ImagesModel(
    @SerializedName("images")
    val images: ArrayList<String>? = ArrayList(),
)
 : Serializable
