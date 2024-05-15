package def.agoramain.common;

import def.agoramain.auth.JwtUtil;
import def.agoramain.retro.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static def.agoramain.common.URL.MEMBER_DETAIL_REQUEST_URL;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final JwtUtil jwtUtil;

    public List<Member> requestMembers(List<Long> memberIds) {

        List<Member> members = new ArrayList<>();

        for (Long memberId : memberIds) {

            ResponseEntity<Member> member = restTemplate
                    .getForEntity(MEMBER_DETAIL_REQUEST_URL.getUrl()+"/"+memberId.toString(), Member.class);

            if (member.getStatusCode().is2xxSuccessful()) members.add(member.getBody());
        }
        return members;
    }

    public Long getMemberIdByToken(String token) {
        return Long.valueOf(jwtUtil.getMemberId(token));
    }
}
