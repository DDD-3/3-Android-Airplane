package com.ddd.airplane.repository.network.service

import com.ddd.airplane.data.response.RecentData
import com.ddd.airplane.data.response.chat.ChatMessageData
import com.ddd.airplane.data.response.chat.ChatRoomData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

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
    ): Single<ChatRoomData>

    /**
     * 채팅방 메세지 조회
     */
    @GET("/api/v1/rooms/{roomId}/messages")
    fun getRoomMessages(
        @Path("roomId") roomId: Int,
        @Query("baseMessageId") baseMessageId: Int,
        @Query("size") size: Int,
        @Query("direction") direction: String
    ): Single<ChatMessageData>

    /**
     * 최근 채팅방 조회
     */
    @GET("/api/v1/recentMessagedRooms")
    fun getRecentRooms(
        @Query("pageNum") pageNum: Int,
        @Query("pageSize") pageSize: Int
    ): Single<RecentData>

}