package com.githubrepo.mappers.dtotoentity

import com.githubrepo.domain.entity.RepositoriesEntity
import com.githubrepo.remote.dto.RespositoriesDto

    fun RespositoriesDto.RepositoryDetails.map() = RepositoriesEntity.RepositoryDetails(
        author = this.author,
        name = this.name,
        url = this.url,
        description = this.description,
        avatar = this.avatar,
        language = this.language,
        languageColor = this.languageColor   ,
        stars = this.stars
    )

fun RespositoriesDto.CatergoryDetailsList.map(counter: Int) = RepositoriesEntity.CatergoryDetailsList(
    questionNumber = counter,
    type = this.type,
    difficulty = this.difficulty,
    category = this.category,
    question = this.question,
    correct_answer = this.correct_answer,
    all_answers = this.incorrect_answers.plus(correct_answer)
)
/*{
    fun getQuestionList() : List<String>{
       return emptyList()
    }
}*/

fun List<RespositoriesDto.RepositoryDetails>.toRepositoryDetails(): List<RepositoriesEntity.RepositoryDetails> {
    val list: ArrayList<RepositoriesEntity.RepositoryDetails> = arrayListOf()
    forEach { item ->
        list.add(item.map())
    }
    return list
}

fun List<RespositoriesDto.CatergoryDetailsList>.toCategoryDetails(): List<RepositoriesEntity.CatergoryDetailsList> {
    val list: ArrayList<RepositoriesEntity.CatergoryDetailsList> = arrayListOf()
    var questionCounter = 1
    forEach { item ->
        list.add(item.map(questionCounter))
        questionCounter++
    }
    return list
}
fun RespositoriesDto.QuizzDetails.toQuizzDetails() = RepositoriesEntity.QuizzDetails(
    response_code = this.response_code,
    results =  this.results.toCategoryDetails()

)
    fun RespositoriesDto.DevelopersDetails.toDeveloperDetails() = RepositoriesEntity.DevelopersDetails(
        username = this.username,
        name = this.name,
        url = this.url,
        type = this.type,
        avatar = this.avatar,
    )
