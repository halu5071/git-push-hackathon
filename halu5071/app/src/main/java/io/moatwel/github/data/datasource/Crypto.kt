/*
 *  GitHub-Client
 *
 *  Crypto.kt
 *
 *  Copyright 2018 moatwel.io
 *  author : halu5071 (Yasunori Horii)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package io.moatwel.github.data.datasource

import android.content.Context
import com.github.gfx.util.encrypt.Encryption
import javax.inject.Inject

class Crypto @Inject constructor(
  private val context: Context
) {

  private var encryption: Encryption?

  init {
    encryption = createEncryption(context)
  }

  private fun createEncryption(context: Context): Encryption? {
    return Encryption(Encryption.getDefaultCipher(), Encryption.getDefaultPrivateKey(context))

  }

  fun encrypt(plainText: String): String {
    return encryption?.encrypt(plainText) ?: plainText
  }

  fun decrypt(encryptedText: String): String {
    return encryption?.decrypt(encryptedText) ?: encryptedText
  }
}