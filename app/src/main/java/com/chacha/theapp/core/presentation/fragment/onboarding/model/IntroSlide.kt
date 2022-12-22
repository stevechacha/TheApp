package com.chacha.theapp.core.presentation.fragment.onboarding.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class IntroSlide(
    val title: String,
    val description: String,
    val icon: String
):Parcelable
