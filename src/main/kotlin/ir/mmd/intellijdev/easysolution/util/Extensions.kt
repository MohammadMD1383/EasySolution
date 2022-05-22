package ir.mmd.intellijdev.easysolution.util

import java.net.URLEncoder

inline val String.urlEncoded: String get() = URLEncoder.encode(this, "UTF-8")
