package com.dolgozitbudet.trainingnewsmvvm.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.dolgozitbudet.trainingnewsmvvm.domain.model.Article
import com.dolgozitbudet.trainingnewsmvvm.presentation.Dimens.ExtraSmallPadding2
import com.dolgozitbudet.trainingnewsmvvm.presentation.Dimens.MediumPaddding1

@Composable
fun ArticlesList(
    modifier: Modifier = Modifier,
    articles: LazyPagingItems<Article>,
    onClick: (Article) -> Unit
) {
    val handlerPagingResult = handlerPagingResult(articles = articles)

    if (handlerPagingResult) {
        LazyColumn(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(MediumPaddding1),
            contentPadding = PaddingValues(all = ExtraSmallPadding2)
        ) {
            items(count = articles.itemCount) {
                articles[it]?.let {
                    ArticleCard(article = it, onClick = { onClick(it) })
                }

            }
        }
    }
}

@Composable
fun handlerPagingResult(
    articles: LazyPagingItems<Article>
): Boolean {

    val loadState = articles.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }
        error != null -> {
            EmptyScreen()
            false
        }
        else -> {
            true
        }
    }

}

@Composable
private fun ShimmerEffect() {
    Column(
        modifier = Modifier.padding(horizontal = MediumPaddding1),
        verticalArrangement = Arrangement.spacedBy(MediumPaddding1)
    ) {
        repeat(10) {
            ArticleCardShimmerEffect(
                modifier = Modifier.padding(horizontal = MediumPaddding1)
            )
        }
    }
}