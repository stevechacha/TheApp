package com.chacha.theapp

import android.app.Application
import com.chacha.theapp.crashlytics.CrashlyticsTree
import com.chacha.theapp.core.data.local.database.FinanceDatabase
import com.chacha.theapp.di.*
import com.chacha.theapp.feature_accounts.presentation.fragment.account.AccountViewModel
import com.chacha.theapp.feature_auth.presentation.fragment.auth.AuthViewModel
import com.chacha.theapp.feature_accounts.presentation.fragment.expense.ExpenseViewModel
import com.chacha.theapp.core.presentation.fragment.home.HomeViewModel
import com.chacha.theapp.feature_accounts.presentation.fragment.income.IncomeViewModel
import com.chacha.theapp.feature_accounts.presentation.fragment.statement.StatementViewModel
import com.google.android.material.color.DynamicColors
import com.squareup.moshi.kotlinx.metadata.internal.metadata.jvm.JvmModuleProtoBuf.Module
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.jetbrains.annotations.NotNull
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module
import timber.log.Timber


open class TheApp : Application() {
    override fun onCreate() {
        super.onCreate()

        DynamicColors.applyToActivitiesIfAvailable(this)

        initTimber()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@TheApp)
            modules(
                databaseModule,
                apiModule,
                environmentModule,
                networkModule,
                registerModule,
                loginModule,
                statementModule,
                incomeModule,
                homeModule,
                authModule,
                accountModule,
                expenseModule,
                transactionsModule,
                createPasswordModule,
                createPinModule,
                loanModule


            )
        }

    }


    private fun initTimber() = when {
        BuildConfig.DEBUG -> {
            Timber.plant(object : Timber.DebugTree() {
                override fun createStackElementTag(@NotNull element: StackTraceElement): String {
                    return super.createStackElementTag(element) + ":" + element.lineNumber
                }
            })
        }
        else -> {
            Timber.plant(CrashlyticsTree())
        }
    }

}