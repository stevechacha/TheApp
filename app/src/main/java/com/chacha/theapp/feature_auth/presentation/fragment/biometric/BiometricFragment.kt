package com.chacha.theapp.feature_auth.presentation.fragment.biometric


import android.content.Intent
import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.biometric.BiometricManager
import com.chacha.theapp.R
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.chacha.theapp.databinding.BiometricFragmentBinding
import com.chacha.theapp.core.util.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import java.util.concurrent.Executor

class BiometricFragment : Fragment(R.layout.biometric_fragment) {
    private val binding by viewBinding (BiometricFragmentBinding::bind)
    private val viewModel : BiometricViewModel by viewModel()
    lateinit var info: String
    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnLogin.isEnabled = false
        //checkDeviceHasBiometric()
        binding.imgFingure.setOnClickListener {
            checkDeviceHasBiometric()
        }

        executor = ContextCompat.getMainExecutor(requireContext())
        biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(
                    errorCode: Int,
                    errString: CharSequence,
                ) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(requireContext(),
                        "Authentication error: $errString", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult,
                ) {
                    super.onAuthenticationSucceeded(result)
                    Toast.makeText(requireContext(),
                        "Authentication succeeded!", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(requireContext(), "Authentication failed",
                        Toast.LENGTH_SHORT)
                        .show()
                }
            })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric login for my app")
            .setSubtitle("Log in using your biometric credential")
            .setDescription("Touch the sensor to authenticate")
            .setNegativeButtonText("Use account password")
            .build()

        // Prompt appears when user clicks "Log in".
        // Consider integrating with the keystore to unlock cryptographic operations,
        // if needed by your app.

        binding.btnLogin.setOnClickListener {
            biometricPrompt.authenticate(promptInfo)
        }

//        biometricPrompt.authenticate(promptInfo)
    }


    @RequiresApi(Build.VERSION_CODES.R)
    private fun checkDeviceHasBiometric() {
        val biometricManager = BiometricManager.from(requireContext())
        when (biometricManager.canAuthenticate(BIOMETRIC_STRONG or DEVICE_CREDENTIAL)) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                Timber.tag("MY_APP_TAG").d("App can authenticate using biometrics.")
                info = "App can authenticate using biometrics."
                binding.btnLogin.isEnabled = true

            }
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                Timber.tag("MY_APP_TAG").e("No biometric features available on this device.")
                info = "No biometric features available on this device."
                binding.btnLogin.isEnabled = false

            }
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                Timber.tag("MY_APP_TAG").e("Biometric features are currently unavailable.")
                info = "Biometric features are currently unavailable."
                binding.btnLogin.isEnabled = false

            }
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                // Prompts the user to create credentials that your app accepts.
                val enrollIntent = Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
                    putExtra(Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                        BIOMETRIC_STRONG or DEVICE_CREDENTIAL)
                }
                binding.btnLogin.isEnabled = false

                startActivityForResult(enrollIntent, 100)
            }
            BiometricManager.BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED -> {
                TODO()
            }
            BiometricManager.BIOMETRIC_ERROR_UNSUPPORTED -> {
                TODO()
            }
            BiometricManager.BIOMETRIC_STATUS_UNKNOWN -> {
                TODO()
            }
        }
        binding.tvMsg.text = info
    }
}


