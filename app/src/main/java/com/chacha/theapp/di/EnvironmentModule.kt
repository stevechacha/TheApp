package com.chacha.theapp.di

import com.chacha.theapp.core.util.Environment
import org.koin.dsl.module

val environmentModule = module {
    single<Environment> { Environment.Main }
}