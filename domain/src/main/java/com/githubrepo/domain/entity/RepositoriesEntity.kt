package com.githubrepo.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

sealed class RepositoriesEntity {

    data class RepositoryDetails(
        val author: String? = null,
        val name: String? = null,
        val avatar: String? = null,
        val url: String? = null,
        val description: String? = null,
        val language: String? = null,
        val languageColor: String? = null,
        val stars: Int? = null,
    ): RepositoriesEntity()

    data class DevelopersDetails(
        val username: String? = null,
        val name: String? = null,
        val avatar: String? = null,
        val url: String? = null,
       val type: String? = null,
    ): RepositoriesEntity()


    @Serializable
    data class QuizzDetails(
        val response_code: String? = null,
        val results: List<CatergoryDetailsList> = emptyList(),
    ): RepositoriesEntity(), java.io.Serializable
    {
        fun getTotalQuestions() = this.results.size
    }

    @Serializable
    data class CatergoryDetailsList(
       val questionNumber: Int = 1,
       val type: String? = null,
       val difficulty: String? = null,
       val category: String? = null,
       val question: String? = null,
       val correct_answer: String? = null,
       val all_answers: List<String> = emptyList(),
    ): RepositoriesEntity(), java.io.Serializable
    {
        fun isSelectedCorrect(answer: String) :Boolean{
            return (answer == correct_answer)
        }
    }
}