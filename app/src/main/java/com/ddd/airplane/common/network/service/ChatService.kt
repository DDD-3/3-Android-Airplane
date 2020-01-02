package com.ddd.airplane.common.network.service

import io.reactivex.Single
import retrofit2.http.*

/**
 * @author jess
 */
interface ChatService {

    /**
     * 채팅방 조회
     */
    @GET("/api/v1/rooms/{roomId}")
    fun getRoom(
        @Path("roomId") roomId: Int
    ): Single<Any>

    /**
     * 채팅방 메세지 조회
     */
    @GET("/api/v1/rooms/{roomId}/messages")
    fun getRoomMessages(
        @Path("roomId") roomId: Int,
        @Query("baseMessageId") baseMessageId: Int,
        @Query("size") size: Int,
        @Query("direction") direction: String
    ): Single<Any>

    /**
     * 최근 채팅방 조회
     */
    @GET("/api/v1/recentMessagedRooms")
    fun getRecentMessagedRooms(
        @Query("pageNum") pageNum: Int,
        @Query("pageSize") pageSize: Int
    ): Single<Any>


}