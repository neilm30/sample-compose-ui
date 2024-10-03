package com.githubrepo.utils

import android.net.Uri
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions

sealed class NavigationCommand {
    data class To(val directions: NavDirections): NavigationCommand()
    data class ToId(val id: Int, val args: Bundle = bundleOf(), val navOptions: NavOptions? = null): NavigationCommand()
    data class ToFragmentId(val id: Int, val args: Bundle = bundleOf(),val navOptions: NavOptions? = null): NavigationCommand()
    object Back: NavigationCommand()
    data class BackTo(val destinationId: Int): NavigationCommand()
    data class ToByDeepLink(val deepLink: Uri): NavigationCommand()
    object Up: NavigationCommand()
}