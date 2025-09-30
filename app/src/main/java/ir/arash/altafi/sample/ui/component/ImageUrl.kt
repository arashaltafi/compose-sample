package ir.arash.altafi.sample.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.disk.DiskCache
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import coil3.request.crossfade
import ir.arash.altafi.sample.R
import okio.Path.Companion.toOkioPath

@Composable
fun ImageUrl(
    url: Any? = null,
    alt: String? = null,
    placeholder: Int = R.drawable.ic_user,
    contentScale: ContentScale? = null,
    modifier: Modifier
) {
    val context = LocalContext.current

    // Remember the image loader so it isn't recreated on each recomposition.
    val cacheDirectory = context.cacheDir.resolve("image_cache").toOkioPath()
    val imageLoader = remember {
        ImageLoader.Builder(context)
            .diskCachePolicy(CachePolicy.ENABLED)  // Enable disk caching
            .memoryCachePolicy(CachePolicy.ENABLED) // Enable memory caching
            .diskCache {
                DiskCache.Builder()
                    .directory(cacheDirectory)
                    .maxSizeBytes(100L * 1024 * 1024) // 100MB cache size
                    .build()
            }
            .build()
    }

    // Remember the ImageRequest if the url is a String.
    val request = if (url is String) {
        remember(url) {
            ImageRequest.Builder(context)
                .data(url)
                .crossfade(true)
                .diskCacheKey(url)
                .memoryCacheKey(url)
                .build()
        }
    } else {
        url
    }

    AsyncImage(
        model = request,
        imageLoader = imageLoader,
//        placeholder = painterResource(placeholder),
        error = painterResource(placeholder),
        fallback = painterResource(placeholder),
        contentDescription = alt,
        modifier = modifier,
        contentScale = contentScale ?: ContentScale.Crop,
    )
}