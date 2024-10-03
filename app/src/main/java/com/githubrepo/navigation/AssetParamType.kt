package com.githubrepo.navigation

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.githubrepo.domain.entity.RepositoriesEntity
import com.google.gson.Gson
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object CustomNavType{
    val QuizType = object : NavType<RepositoriesEntity.QuizzDetails>(
        isNullableAllowed = false
    ){
        override fun get(bundle: Bundle, key: String): RepositoriesEntity.QuizzDetails? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): RepositoriesEntity.QuizzDetails {
            // return Gson().fromJson(value, RepositoriesEntity.QuizzDetails::class.java)
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: RepositoriesEntity.QuizzDetails): String {
            return Uri.encode(Json.encodeToString(value))
        }
        override fun put(bundle: Bundle, key: String, value: RepositoriesEntity.QuizzDetails) {
            bundle.putString(key,Json.encodeToString(value) )
        }

    }
}
