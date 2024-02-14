package com.example.apitestapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.apitestapp.model.ContentDB
import com.example.apitestapp.model.ContentDBItem
import com.example.apitestapp.repository.Repository
import com.example.apitestapp.viewmodel.CollegeVM
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class CollegeVMTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: Repository
    lateinit var collegeVM: CollegeVM
    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(dispatcher)
        collegeVM = CollegeVM(repository)
    }

    @Test
    fun fetchApi() = runTest {
        val content = ContentDB()
        content.add(ContentDBItem("", "", ""))
        content.add(ContentDBItem("", "", ""))
        content.add(ContentDBItem("", "", ""))
        Mockito.`when`(repository.getData()).thenReturn(content)
        dispatcher.scheduler.advanceUntilIdle()
        val repository = collegeVM.collegeStateFlow.value
        print(repository)
        Assert.assertEquals(repository, content)
    }

    @After
    fun close() {
        Dispatchers.shutdown()
    }
}