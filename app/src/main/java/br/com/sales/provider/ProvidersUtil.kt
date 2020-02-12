package br.com.sales.provider

import android.net.Uri

class ProvidersUtil {

    companion object {
        fun idFromUri(uri: Uri): Any = uri.pathSegments.get(1)
    }
}