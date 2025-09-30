package ir.arash.altafi.sample.utils.ext

fun Any.getHashLong(): Long {
    return this.hashCode().toLong()
}

fun Any.getHashInt(): Int {
    return this.hashCode()
}
