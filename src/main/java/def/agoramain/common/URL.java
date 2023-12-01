package def.agoramain.common;

import lombok.Getter;

@Getter
public enum URL {
    MEMBER_DETAIL_REQUEST_URL("http://localhost:8080/members");

    private final String url;

    URL(String url) {
        this.url = url;
    }
}
