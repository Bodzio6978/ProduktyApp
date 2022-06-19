package com.gmail.bogumilmecel2.produkty.feature_items.domain.model

@kotlinx.serialization.Serializable
data class ImageLink(
    val default_link: String = "",
    val small: String = ""
):Comparable<ImageLink>{
    override fun compareTo(other: ImageLink): Int {
        if (this.default_link>other.default_link) return 1
        if (this.default_link<other.default_link) return -1
        if (this.small>other.small) return -1
        if (this.small<other.small) return -1
        return 0
    }
}