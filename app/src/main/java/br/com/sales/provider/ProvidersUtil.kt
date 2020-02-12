package br.com.sales.provider

import android.content.ContentUris
import android.net.Uri

class ProvidersUtil {

    companion object {
        fun idFromUri(uri: Uri): Any = uri.pathSegments[1]

        fun buildUriWithId(uri: Uri, id: Long): Uri = ContentUris.withAppendedId(uri, id)
    }
}