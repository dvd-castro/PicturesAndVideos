package br.com.davidcastro.ui.utils.extensions

import android.net.Uri
import br.com.davidcastro.data.model.PhotoResponse
import com.google.gson.Gson

fun PhotoResponse.toJsonString() : String =
    Uri.encode(Gson().toJson(this))