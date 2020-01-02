package com.ddd.airplane.common.model

/**
 * 통신에러
 *
 * @property error
 * @property error_description
 */
data class ErrorResponse(
    val error: String?,
    val error_description: String?
)