package com.ddd.airplane.repository.network.service

import com.ddd.airplane.data.response.RecentData
import com.ddd.airplane.data.response.chat.ChatMessageData
import com.ddd.airplane.data.response.chat.ChatRoomData
import com.ddd.airplane.repository.network.config.ServerUrl
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
    @GET(ServerUrl.GET_ROOM_INFO)
    fun getRoom(
        @Path("roomId") roomId: Int
    ): Single<ChatRoomData>

    /**
     * 채팅방 메세지 조회
     */
    @GET(ServerUrl.GET_ROOM_MESSAGE)
    fun getRoomMessages(
        @Path("roomId") roomId: Int,
        @Query("baseMessageId") baseMessageId: Int,
        @Query("size") size: Int,
        @Query("direction") direction: String
    ): Single<ChatMessageData>

    /**
     * 최근 채팅방 조회
     */
    @GET(ServerUrl.GET_RECENT_ROOM)
    fun getRecentRooms(
        @Query("pageNum") pageNum: Int,
        @Query("pageSize") pageSize: Int
    ): Single<RecentData>

}