package com.ddd.airplane.data.response

import com.ddd.airplane.data.response.chat.ChatRoomData

/**
 * 최근 채팅한 채팅방 조회
 */
data class RecentData(
    val items: ArrayList<ChatRoomData>?,
    val pageInfo: PageInfoData?
)