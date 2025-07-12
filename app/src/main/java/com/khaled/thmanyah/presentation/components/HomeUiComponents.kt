package com.khaled.thmanyah.presentation.components

import com.khaled.thmanyah.domain.model.SectionUiModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import com.khaled.thmanyah.domain.model.ContentItemUiModel
import androidx.compose.material3.Icon


@Composable
fun HeaderUI(
    title: String,
    onSearchClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = Color.White
        )

        IconButton(onClick = onSearchClick) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = Color.White
            )
        }
    }
}

@Composable
fun SectionHeader(section: SectionUiModel) {
    Column(modifier = Modifier.fillMaxWidth()
        .padding(top = 16.dp, bottom = 8.dp)) {
        Text(
            text = section.name,
            color = Color.White,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 4.dp)
        )
    }
}

@Composable
fun SquareSection(content: List<ContentItemUiModel>) {
        LazyRow(contentPadding = PaddingValues(horizontal = 16.dp)) {
            items(content) { item ->
                ContentCard(item)
            }
        }
    }


@Composable
fun BigSquareSection(content: List<ContentItemUiModel>) {
        LazyRow(contentPadding = PaddingValues(horizontal = 16.dp)) {
            items(content) { item ->
                ContentCard(item, isBig = true)
            }
        }
}

@Composable
fun TwoLineGridSection(content: List<ContentItemUiModel>) {
    val chunked = content.chunked(2) // group items into vertical pairs

    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        items(chunked) { pair ->
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .width(120.dp) // Set desired item width
            ) {
                pair.forEach { item ->
                    ContentCard(item)
                }
            }
        }
    }
}

@Composable
fun QueueSection(content: List<ContentItemUiModel>) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp) // space between items horizontally
    ) {
        items(content) { item ->
            Row(
                modifier = Modifier
                    .width(280.dp) // fixed width for each item, adjust as needed
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ContentImage(item.imageUrl, modifier = Modifier.size(60.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = item.name,
                        color = Color.White,
                        style = MaterialTheme.typography.bodyMedium)
                    Text(
                        text = item.description.trim(),
                        color = Color.White,
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.align(Alignment.Start)
                    )
                }
            }
        }
    }
}


@Composable
fun ContentCard(item: ContentItemUiModel, isBig: Boolean = false) {
    Column(
        modifier = Modifier
            .width(if (isBig) 180.dp else 120.dp)
            .padding(end = 8.dp)
    ) {
        ContentImage(item.imageUrl, modifier = Modifier
            .height(if (isBig) 180.dp else 120.dp)
            .fillMaxWidth())
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = item.name,
            color = Color.White,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun ContentImage(url: String, modifier: Modifier = Modifier) {
    AsyncImage(
        model = url,
        contentDescription = null,
        modifier = modifier.clip(RoundedCornerShape(8.dp)),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun LoadingIndicator() {
    // Replace with your loading UI
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = Color.White)
    }
}

@Composable
fun ErrorView(exception: Throwable) {
    // Replace with your error UI
    Box(modifier = Modifier.fillMaxSize(),contentAlignment = Alignment.Center
    ) {
        Text(text = "Error: ${exception.localizedMessage}",
            color = Color.White
        )
    }
}