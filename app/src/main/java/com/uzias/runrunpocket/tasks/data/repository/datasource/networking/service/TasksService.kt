package com.uzias.runrunpocket.tasks.data.repository.datasource.networking.service

import com.uzias.runrunpocket.tasks.data.repository.datasource.networking.model.JsonTask
import io.reactivex.Completable
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TasksService {

    @GET("tasks?responsible_id=")
    fun getTasks(): Observable<List<JsonTask>>

    @POST("tasks/{id}/play")
    fun playTask(@Path("id") id: Long): Completable

    @POST("tasks/{id}/pause")
    fun pauseTask(@Path("id") id: Long): Completable

    @POST("tasks/{id}/close")
    fun closeTask(@Path("id") id: Long): Completable

}