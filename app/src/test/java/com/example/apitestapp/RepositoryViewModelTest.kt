package com.example.apitestapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.apitestapp.model.ContentDBItem
import com.example.apitestapp.repository.Repository
import com.example.apitestapp.viewmodel.CollegeVM
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class RepositoryViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mockRepository: Repository
    private lateinit var viewModel: CollegeVM
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = CollegeVM(mockRepository)
    }

    @Test
    fun testFetchRepositories() = runTest {
        val mockRepositories = arrayListOf(
            ContentDBItem(dbn = "", overview_paragraph = "", school_name = ""),
            ContentDBItem(dbn = "", overview_paragraph = "", school_name = ""),
            ContentDBItem(dbn = "", overview_paragraph = "", school_name = ""),
            ContentDBItem(dbn = "", overview_paragraph = "", school_name = "")
        )
        `when`(mockRepository.getData())
        testDispatcher.scheduler.advanceUntilIdle()
        val repositories = viewModel.getData()
        assertEquals(mockRepositories, repositories)
    }

    @After
    fun close() {
        Dispatchers.shutdown()
    }
}
