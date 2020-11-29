package com.publicissapient.publicissapienttest.networkingtest

import com.nhaarman.mockitokotlin2.mock
import com.publicissapient.publicissapienttest.models.datamodel.Offers
import com.publicissapient.publicissapienttest.netwroks.ResponseHandler
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.HttpException
import retrofit2.Response
import java.net.SocketTimeoutException

@RunWith(JUnit4::class)
class ResponseHandlerTest {
    lateinit var responseHandler: ResponseHandler

    @Before
    fun setUp() {
        responseHandler = ResponseHandler()
    }

    @Test
    fun `when exception code is 404 then return not found`() {
        val httpException = HttpException(Response.error<Offers>(404, mock()))
        val result = responseHandler.handleException<Offers>(httpException)
        Assert.assertEquals("Not Found", result.message)
    }

    @Test
    fun `when timeout then return timeout error`() {
        val socketTimeoutException = SocketTimeoutException()
        val result = responseHandler.handleException<Offers>(socketTimeoutException)
        Assert.assertEquals("Something went wrong", result.message)
    }
}