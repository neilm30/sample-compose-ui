package com.githubrepo.domain.entity

sealed class ErrorEntity {
    data class Error(val errorCode: String? = "", val errorMessage: String? = "") : ErrorEntity()
}