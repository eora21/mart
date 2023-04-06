package com.nhnacademy.mart_servlet.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Error {
    NO_CART("장바구니가 비어있습니다.", "/foods.do", "장바구니 채우러 가기"),
    NOT_ENOUGH_MONEY("금액이 부족합니다.", "/logout.do", "마트 나가기"),
    REQUIRE_INIT("진열대에 물품이 부족합니다.", "/init.do", "진열대 채우러 가기"),
    NOT_VALID("옳지 않은 값을 입력하셨습니다.", "/foods.do", "상품 다시 고르기");

    private final String errorExplain;
    private final String href;
    private final String to;
}
