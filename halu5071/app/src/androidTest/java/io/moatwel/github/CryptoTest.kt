/*
 *  GitHub-Client
 *
 *  CryptoTest.kt
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

package io.moatwel.github

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import io.moatwel.github.data.datasource.Crypto
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CryptoTest {

  private lateinit var crypto: Crypto
  private val plainText = "SamplePlainTextGitHubToken12345"
  private lateinit var contextBefore: Context
  private lateinit var contextAfter: Context

  @Before
  fun before() {
    contextBefore = InstrumentationRegistry.getTargetContext().applicationContext
    contextAfter = InstrumentationRegistry.getTargetContext().applicationContext
  }

  @Test
  fun testDifferentContext() {
    assertNotEquals(contextBefore, `not`(contextAfter))
  }

  @Test
  fun testEncryption() {
    crypto = Crypto(contextBefore)

    val encryptedText = crypto.encrypt(plainText)

    assertThat(plainText, `not`(encryptedText))
  }

  @Test
  fun testDecryption() {
    crypto = Crypto(contextAfter)

    val encryptedText = "PYp6Sg2wC9Lt5Sz/8DFmItnlvxOsSdUE8IhT/drWi3g8UsCN+7aOtJM76pTRS12k"

    val decryptedText = crypto.decrypt(encryptedText)

    assertThat(encryptedText, `not`(decryptedText))
    assertThat(decryptedText, `is`(plainText))
  }
}