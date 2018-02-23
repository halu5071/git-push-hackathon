/*
 *  GitHub-Client
 *
 *  AuthDataDataRepository.kt
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

package io.moatwel.github.data.repository

import io.moatwel.github.data.BuildConfig
import io.moatwel.github.data.datasource.AuthDataDataSource
import io.moatwel.github.domain.entity.AuthData
import io.moatwel.github.domain.repository.AuthDataRepository
import io.reactivex.Observable

/**
 *  This class is an implementation class of [AuthDataRepository] of domain layer.
 *  This class read and delete auth data from somewhere, and save it on somewhere.
 *
 *  Actual operation is implemented on [AuthDataDataSource].
 */
class AuthDataDataRepository (
  private val dataSource: AuthDataDataSource
) : AuthDataRepository {

  private var authData: AuthData? = null

  override fun save(authData: AuthData) {
    this.authData = authData
    dataSource.saveToSharedPreference(authData)
  }

  /**
   *  get my auth data from somewhere
   *
   *  @return AuthData?
   */
  override fun get(): AuthData? {
    authData?.let {
      return it
    } ?: run {
      val authData = dataSource.readFromSharedPreference()
      this.authData = authData
      return this.authData
    }
  }

  override fun delete() {
    dataSource.removeFromSharedPreference()
  }

  override fun fetch(code: String): Observable<AuthData> {
    return dataSource.fetchFromApi(code, BuildConfig.CLIENT_ID, BuildConfig.CLIENT_SECRET)
      .map { geneAuthDataFromResponse(it) }
  }

  private fun geneAuthDataFromResponse(response: String): AuthData {
    val str = response.split("&")
    return AuthData(str[0].substring(13))
  }
}