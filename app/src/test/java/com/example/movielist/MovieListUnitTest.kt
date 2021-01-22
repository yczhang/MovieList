package com.example.movielist

import android.app.Application
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.movielist.viewmodels.MainViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@RunWith(MockitoJUnitRunner::class)
class MovieListUnitTest {
    private lateinit var viewModel : MainViewModel

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setup() {

        MockitoAnnotations.initMocks(this);

        viewModel = MainViewModel(Application())
    }

    @Test
    fun TestDataProcessing()
    {
        viewModel.loadLocalMovieList(R.raw.sampledata)

        assert(viewModel.getItemsCount() != 50)
    }
}