package com.example.account.di

import com.example.account.ui.AccountViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val accountModule = module {
    viewModelOf(::AccountViewModel)
}