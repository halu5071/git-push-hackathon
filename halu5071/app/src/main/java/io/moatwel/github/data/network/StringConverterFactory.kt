package io.moatwel.github.data.network

import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

object StringConverterFactory : Converter.Factory() {

  private val MEDIA_TYPE = MediaType.parse("text/plain")

  fun create(): StringConverterFactory {
    return StringConverterFactory
  }

  override fun requestBodyConverter(type: Type?,
                                    parameterAnnotations: Array<out Annotation>?,
                                    methodAnnotations: Array<out Annotation>?,
                                    retrofit: Retrofit?): Converter<*, RequestBody>? {

    if (type == String::class.java) {
      return Converter<String, RequestBody> {
        RequestBody.create(MEDIA_TYPE, it)
      }
    }
    return null
  }

  override fun responseBodyConverter(type: Type?,
                                     annotations: Array<out Annotation>?,
                                     retrofit: Retrofit?): Converter<ResponseBody, *>? {
    if (type == String::class.java) {
      return Converter<ResponseBody, String> {
        it.string().toString()
      }
    }

    return null
  }

}