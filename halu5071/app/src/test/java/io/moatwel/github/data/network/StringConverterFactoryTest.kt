package io.moatwel.github.data.network

import io.moatwel.github.TestUtil
import io.moatwel.github.data.network.retrofit.AccessTokenApi
import io.reactivex.observers.TestObserver
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers.`is`
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class StringConverterFactoryTest {

  private lateinit var mockServer: MockWebServer
  private lateinit var retrofit: Retrofit
  private val resource: String = TestUtil.readSource("access_token.txt")

  @Before
  fun before() {
    mockServer = MockWebServer()
    retrofit = Retrofit.Builder()
      .baseUrl(mockServer.url("").toString())
      .addConverterFactory(StringConverterFactory)
      .addConverterFactory(MoshiConverterFactory.create())
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .build()
  }

  @Test
  fun testGetAccessToken() {
    mockServer.enqueue(MockResponse().setBody(resource))

    val accessTokenApi = retrofit.create(AccessTokenApi::class.java)

    val resultObservable = accessTokenApi.fetchAccessToken(anyString(), anyString(), anyString())

    assertNotNull(resultObservable)

    resultObservable.test()
      .assertValue(resource)
      .assertComplete()
  }

  @After
  fun after() {
    mockServer.shutdown()
  }
}