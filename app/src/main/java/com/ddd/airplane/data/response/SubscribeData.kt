package com.ddd.airplane.data.response

import com.ddd.airplane.data.response.chat.ChatRoomData

/**
 * 구독한 주제 채팅방 조회
 */
data class SubscribeData(
    val items: ArrayList<ChatRoomData>?,
    val pageInfo: PageInfoData?
)