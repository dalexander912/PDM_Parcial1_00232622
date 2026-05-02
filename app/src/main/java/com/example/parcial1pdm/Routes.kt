package com.example.parcial1pdm

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Routes : NavKey {
  @Serializable
  data object Menu : Routes()

  @Serializable
  data object Order : Routes()
}