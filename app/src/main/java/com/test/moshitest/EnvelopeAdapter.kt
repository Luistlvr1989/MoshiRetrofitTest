package com.test.moshitest

import com.squareup.moshi.*
import java.io.IOException
import java.lang.reflect.Type

class EnvelopeAdapter constructor(private val delegate: JsonAdapter<Envelope<*>>) : JsonAdapter<Any>() {
    companion object {
        val FACTORY: JsonAdapter.Factory = object : Factory {
            override fun create(type: Type, annotations: Set<Annotation>, moshi: Moshi): JsonAdapter<*>? {
                val delegateAnnotations = Types.nextAnnotations(annotations, Enveloped::class.java) ?: return null
                val envelope = Types.newParameterizedTypeWithOwner(EnvelopeAdapter::class.java, Envelope::class.java, type)
                val delegate: JsonAdapter<Envelope<*>> = moshi.nextAdapter(this, envelope, delegateAnnotations)
                return EnvelopeAdapter(delegate)
            }
        }
    }

    @Throws(IOException::class)
    override fun fromJson(reader: JsonReader): Any? {
        return delegate.fromJson(reader)?.response
    }

    @Throws(IOException::class)
    override fun toJson(writer: JsonWriter, value: Any?) {
        delegate.toJson(writer, Envelope(value))
    }

    data class Envelope<T>(@Json(name = "data") var response: T?)
}