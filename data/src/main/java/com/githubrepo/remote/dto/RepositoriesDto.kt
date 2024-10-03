package com.githubrepo.remote.dto

import com.google.gson.annotations.SerializedName

sealed class RespositoriesDto {

    data class RepositoryDetails(
        @SerializedName("author") val author: String? = null,
        @SerializedName("name") val name: String? = null,
        @SerializedName("avatar") val avatar: String? = null,
        @SerializedName("url") val url: String? = null,
        @SerializedName("description") val description: String? = null,
        @SerializedName("language") val language: String? = null,
        @SerializedName("languageColor") val languageColor: String? = null,
        @SerializedName("stars") val stars: Int? = null,
    ): RespositoriesDto()

    data class DevelopersDetails(
        @SerializedName("username") val username: String? = null,
        @SerializedName("name") val name: String? = null,
        @SerializedName("avatar") val avatar: String? = null,
        @SerializedName("url") val url: String? = null,
        @SerializedName("type") val type: String? = null,
    ): RespositoriesDto()

    data class QuizzDetails(
        @SerializedName("response_code") val response_code: String? = null,
        @SerializedName("results") val results: List<CatergoryDetailsList> = emptyList(),
    ): RespositoriesDto()

    data class CatergoryDetailsList(
        @SerializedName("type") val type: String? = null,
        @SerializedName("difficulty") val difficulty: String? = null,
        @SerializedName("category") val category: String? = null,
        @SerializedName("question") val question: String? = null,
        @SerializedName("correct_answer") val correct_answer: String,
        @SerializedName("incorrect_answers") val incorrect_answers: List<String> = emptyList(),
    ): RespositoriesDto()
}