package com.ddd.airplane.common.consts

object Home {

    /**
     * 홈 스타일
     */
    enum class Style(val style: String) {
        MAIN_SWIPE_BANNER("mainSwipeBanner"),
        RECTANGLE_BANNER("rectangleBanner"),
        HORIZONTAL("horizontal"),
        GRID_2("grid2"), // Grid, Span 2
        RANK("rank"),
        EMPTY("empty")
    }
}

