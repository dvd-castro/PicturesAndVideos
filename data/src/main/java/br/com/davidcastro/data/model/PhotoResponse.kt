package br.com.davidcastro.data.model

import com.google.gson.annotations.SerializedName

data class PhotoResponse(
   @SerializedName("next_page") val next_page: String,
   @SerializedName("page") val page: Int,
   @SerializedName("per_page") val per_page: Int,
   @SerializedName("photos") val photos: List<Photo>,
   @SerializedName("total_results") val total_results: Int
)

data class Photo(
    @SerializedName("alt") val alt: String,
    @SerializedName("avg_color") val avg_color: String,
    @SerializedName("height") val height: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("photographer") val photographer: String,
    @SerializedName("photographer_id") val photographer_id: Int,
    @SerializedName("photographer_url") val photographer_url: String,
    @SerializedName("src") val src: Src,
    @SerializedName("url") val url: String,
    @SerializedName("width") val width: Int
)

data class Src(
    @SerializedName("landscape") val landscape: String,
    @SerializedName("large") val large: String,
    @SerializedName("large2x") val large2x: String,
    @SerializedName("medium") val medium: String,
    @SerializedName("original") val original: String,
    @SerializedName("portrait") val portrait: String,
    @SerializedName("small") val small: String,
    @SerializedName("tiny") val tiny: String
)