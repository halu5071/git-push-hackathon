/*
 *  GitHub-Client
 *
 *  AccessTokenApi.kt
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

package io.moatwel.github.data.network.retrofit

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface AccessTokenApi {
  @GET("/login/oauth/access_token")
  fun fetchAccessToken(
    @Query("code") code: String,
    @Query("client_id") clientId: String,
    @Query("client_secret") clientSecret: String
  ): Observable<String>
}