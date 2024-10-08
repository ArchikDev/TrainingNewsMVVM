package com.dolgozitbudet.trainingnewsmvvm.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.dolgozitbudet.trainingnewsmvvm.presentation.Dimens.MediumPaddding1
import com.dolgozitbudet.trainingnewsmvvm.presentation.common.ArticlesList
import com.dolgozitbudet.trainingnewsmvvm.presentation.common.SearchBar
import com.dolgozitbudet.trainingnewsmvvm.presentation.nvgraph.Route

@Composable
fun SearchScreen(
    state: SearchState,
    event: (SearchEvent) -> Unit,
    navigate: (String) -> Unit
) {
    Column(modifier = Modifier
        .padding(
            top = MediumPaddding1,
            start = MediumPaddding1,
            end = MediumPaddding1,
        )
        .statusBarsPadding()
        .fillMaxSize()
    ) {
        SearchBar(
            modifier = Modifier.padding(horizontal = MediumPaddding1),
            text = state.searchQuery,
            readOnly = false,
            onValueChange = {
                event(SearchEvent.UpdateSearchQuery(it))
            },
            onSearch = {
                event(SearchEvent.SearchNews)
            },
        )

        Spacer(modifier = Modifier.height(MediumPaddding1))

        state.articles?.let {
            val articles = it.collectAsLazyPagingItems()
            ArticlesList(articles = articles, onClick = {
                navigate(Route.DetailsScreen.route)
            })
        }
    }
}