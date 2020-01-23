package com.ddd.airplane.presenter.home.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ddd.airplane.R
import com.ddd.airplane.common.base.BaseViewModel
import com.ddd.airplane.common.consts.Home
import com.ddd.airplane.data.response.chat.ChatRoomData
import com.ddd.airplane.data.response.chat.SubjectData
import com.ddd.airplane.data.response.home.HomeData
import com.ddd.airplane.data.response.home.BannerData

class HomeViewModel(application: Application) : BaseViewModel(application) {

    private val _homeList = MutableLiveData<ArrayList<HomeData.ItemData<Any>>>()
    val homeList: LiveData<ArrayList<HomeData.ItemData<Any>>> = _homeList

    /**
     * 홈 데이터
     */
    fun getHomeList() {

        // 홈 리스트
        val itemList = ArrayList<HomeData.ItemData<Any>>()

        // swipe
        val swipeBanner = ArrayList<BannerData>().apply {
            add(BannerData("혼자 방송 볼 때\n심심하다면?\n사바사!", R.drawable.img_main_banner_01))
            add(BannerData("사이더들과\n오늘 밤\n달려요!", R.drawable.img_main_banner_02))
            add(BannerData("구독 방송\n핫한 방송\n한번에!", R.drawable.img_main_banner_03))
        }
        itemList.add(
            HomeData.ItemData(
                style = Home.Style.MAIN_SWIPE_BANNER, item = swipeBanner
            )
        )

        // 최근 본 방송
        val recent = ArrayList<ChatRoomData>().apply {

            add(
                ChatRoomData(
                    userCount = 140,
                    subject = SubjectData(
                        name = "남산의 부장",
                        description = "“각하,  제가 어떻게 하길 원하십니까”\n" +
                                "\n" +
                                "1979년 10월 26일, 중앙정보부장 김규평(이병헌)이 대한민국 대통령을 암살한다.\n" +
                                "\n" +
                                "이 사건의 40일전, 미국에서는 전 중앙정보부장 박용각(곽도원)이 \n" +
                                "청문회를 통해 전 세계에 정권의 실체를 고발하며 파란을 일으킨다.\n" +
                                "그를 막기 위해 중앙정보부장 김규평과 경호실장 곽상천(이희준)이 나서고,\n" +
                                "대통령 주변에는 충성 세력과 반대 세력들이 뒤섞이기 시작하는데…",
                        subscribeCount = 10,
                        thumbnail = "http://img.cgv.co.kr/Movie/Thumbnail/Poster/000082/82999/82999_320.jpg"
                    )
                )
            )

            add(
                ChatRoomData(
                    userCount = 423,
                    subject = SubjectData(
                        name = "히트맨",
                        description = "웹툰 작가가 되고 싶어 국정원을 탈출한 \n" +
                                "비밀 프로젝트 방패연 출신 전설의 암살요원 '준'.\n" +
                                "그러나 현실은 연재하는 작품마다 역대급 악플만 받을 뿐이다.\n" +
                                "술김에 그리지 말아야 할 1급 기밀을 그려버리고\n" +
                                "예상치 않게 웹툰은 하루아침에 초대박이 나지만,\n" +
                                "그로 인해 '준'은 국정원과 테러리스트의 더블 타깃이 되는데...",
                        subscribeCount = 23,
                        thumbnail = "http://img.cgv.co.kr/Movie/Thumbnail/Poster/000083/83039/83039_320.jpg"
                    )
                )
            )

            add(
                ChatRoomData(
                    userCount = 10,
                    subject = SubjectData(
                        name = "미스터 주- 사라진 VIP",
                        description = "동물의 말이 들리는 순간, \n" +
                                "수사의 파트너가 바뀐다! \n" +
                                "\n" +
                                "국가정보국 에이스 요원 태주.\n" +
                                "특사로 파견된 VIP 경호 임무를 수행하던 중, \n" +
                                "갑작스러운 사고로 VIP는 사라지고\n" +
                                "설상가상 온갖 동물들의 말이 들리기 시작한다.\n" +
                                "갑자기 이상한 행동을 하는 태주를 의심하는 민국장과 만식을 뒤로 하고,\n" +
                                "태주는 군견 알리와 함께 VIP를 찾아 나서는데…",
                        subscribeCount = 10,
                        thumbnail = "http://img.cgv.co.kr/Movie/Thumbnail/Poster/000083/83004/83004_320.jpg"
                    )
                )
            )

        }
        itemList.add(
            HomeData.ItemData(
                Home.Style.HORIZONTAL, "최근 본 방송", recent
            )
        )

//        itemList.add(
//            HomeData.ItemData(
//                Home.Style.RECTANGLE_BANNER,
//                item = RectangleData("http://post.phinf.naver.net/MjAxOTExMTlfMzEg/MDAxNTc0MTQ4OTk4NDY0.8fQgfiGzgT2VG6nHBQyzr2g4l8TVXly6bfvnRixgqHYg.FgSVk6X1gCbUn1ow8aesNvG1NRNVbhCRtT10kOGLDHcg.PNG/IODRXw2DPRQxOrN-Gs1pESJBJGXU.jpg")
//            )
//        )

        _homeList.value = itemList
    }

    fun getStyle(position: Int) = homeList.value?.get(position)?.style ?: Home.Style.EMPTY

}
