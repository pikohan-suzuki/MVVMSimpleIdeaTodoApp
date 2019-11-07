package com.amebaownd.pikohan_nwiatori.idea.util

import androidx.fragment.app.Fragment
import com.amebaownd.pikohan_nwiatori.idea.ViewModelFactory

fun Fragment.getViewModelFactory(): ViewModelFactory {
    val userListRepository =
        ServiceLoader.provideRepository(requireContext())
    return ViewModelFactory(userListRepository)
}