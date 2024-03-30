package def.agoramain.common;

import lombok.Getter;

@Getter
public enum URL {
    /** 도커를 사용할 때는 account 서비스를 사용..... */
    MEMBER_DETAIL_REQUEST_URL("http://account:8080/members");
    /** 임시적, gateway 를 사용할 때는 localhost 사용.. */
//    MEMBER_DETAIL_REQUEST_URL("http://localhost:8080/members");

    private final String url;

    URL(String url) {
        this.url = url;
    }
}
