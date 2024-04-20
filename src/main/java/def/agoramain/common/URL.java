package def.agoramain.common;

import lombok.Getter;

@Getter
public enum URL {
    /** 도커를 사용할 때는 account 서비스를 사용..... */
//    MEMBER_DETAIL_REQUEST_URL("http://account:8080/members");
    /** cloudType */
    MEMBER_DETAIL_REQUEST_URL("https://port-0-agora-account-85phb42bluzlb3li.sel5.cloudtype.app/members");

    private final String url;

    URL(String url) {
        this.url = url;
    }
}
