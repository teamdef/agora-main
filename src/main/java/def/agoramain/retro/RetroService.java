package def.agoramain.retro;

import def.agoramain.retro.dto.ReqRetroDto;
import def.agoramain.retro.dto.RetroDetailDto;
import def.agoramain.retro.dto.RetroDto;
import def.agoramain.retro.entity.*;
import def.agoramain.retro.keep.repo.KeepRepo;
import def.agoramain.retro.problem.dto.response.ProblemDetailResDto;
import def.agoramain.retro.problem.entity.Problem;
import def.agoramain.retro.problem.entity.Try;
import def.agoramain.retro.problem.repo.ProblemRepo;
import def.agoramain.retro.problem.repo.TryRepo;
import def.agoramain.retro.repo.RetroMemberRepo;
import def.agoramain.retro.repo.RetroRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static def.agoramain.common.URL.MEMBER_DETAIL_REQUEST_URL;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RetroService {

    private final KeepRepo keepRepo;
    private final ProblemRepo problemRepo;
    private final TryRepo tryRepo;
    private final RestTemplate restTemplate = new RestTemplate();
    private final RetroRepo retroRepo;
    private final RetroMemberRepo retroMemberRepo;

    // TODO: 하.. 일단 되게만 하고 쿼리하는 부분 다 바꿔야 함
    public List<RetroDto> findRetros(Long projectId, Pageable page) {

        Page<Retro> retros = retroRepo.findAllByProjectIdOrderByCreateTime(projectId, page);

        // member 들의 unique Set 을 구해서 한번에 요청 하기 위한 용도임
        Set<Long> uniqueMemberIds = new HashSet<>();

        List<List<Long>> membersInRetros = retros.stream().map(retro -> {
            List<Long> memberIds = retroMemberRepo.findAllByRetroId(retro.getId())
                    .stream().map(RetroMember::getMemberId).toList();
            uniqueMemberIds.addAll(memberIds);
            return memberIds;
        }).toList();

        // member 정보 조회
        List<Member> members = requestMembers(uniqueMemberIds.stream().toList());
        Map<Long, Member> memberMap = members
                .stream()
                .collect(Collectors.toMap(Member::getId, Function.identity()));

        Iterator<Retro> retrosIt = retros.getContent().stream().iterator();
        Iterator<List<Long>> memberIdsIt = membersInRetros.iterator();
        List<RetroDto> retroDtos = new ArrayList<>();

        while(retrosIt.hasNext() && memberIdsIt.hasNext()){
            List<Member> engagedMembers = memberIdsIt
                    .next()
                    .stream()
                    .map(memberMap::get)
                    .toList();
            retroDtos.add(new RetroDto(retrosIt.next(), engagedMembers));
        }

        return retroDtos;
    }

    @Transactional
    public void makeRetro(ReqRetroDto retroDto) {

        Retro retro = retroRepo.save(retroDto.toEntity());
        mapRetroMembers(retro.getId(), retroDto);

    }

    @Transactional
    public void deleteRetro(Long retroId) {

        retroRepo.deleteById(retroId);
        retroMemberRepo.deleteAllByRetroId(retroId);
    }

    @Transactional
    public void deleteRetroMember(Long retroId, List<Long> memberIds) {

        List<RetroMember> retroMembers = retroMemberRepo.findAllByRetroId(retroId);
        List<RetroMember> filterMembers = retroMembers
                .stream()
                .filter(retroMember -> memberIds.contains(retroMember.getMemberId()))
                .collect(Collectors.toList());

        retroMemberRepo.deleteAll(filterMembers);
    }

    public RetroDetailDto findRetroDetail(Long retroId) throws Exception{

        Retro retro = retroRepo.findById(retroId).orElseThrow(ClassNotFoundException::new);
        List<Long> memberIds = retroMemberRepo.findAllByRetroId(retroId)
                .stream()
                .map(RetroMember::getMemberId)
                .collect(Collectors.toList());

        // TODO: retro_member 테이블 활용 필요
        Long creatorId = retro.getCreateMemberId();
        memberIds.add(creatorId);

        List<Member> members = requestMembers(memberIds);
        List<Keep> keeps = keepRepo.findAllByRetroId(retroId);
        List<Problem> problems = problemRepo.findAllByRetroId(retroId);

        // TODO: 그.. join 사용하는 방법 적용해서 쿼리 날려야 함.
        List<ProblemDetailResDto> problemResults = problems
                .stream()
                .map(problem -> {
                    List<Try> tries = tryRepo.findAllByProblemId(problem.getId());
                    return new ProblemDetailResDto(problem, tries);
                }).toList();

        Member creator = members.stream()
                .filter(member-> member.getId().equals(creatorId))
                .toList()
                .get(0);

        return new RetroDetailDto(retro, members, problemResults, keeps, creator);

    }

    public List<Member> requestMembers(List<Long> memberIds) {

        List<Member> members = new ArrayList<>();

        for (Long memberId : memberIds) {

            ResponseEntity<Member> member = restTemplate
                    .getForEntity(MEMBER_DETAIL_REQUEST_URL.getUrl()+"/"+memberId.toString(), Member.class);

            if (member.getStatusCode().is2xxSuccessful()) members.add(member.getBody());
        }
        return members;
    }

    private void mapRetroMembers(Long retroId, ReqRetroDto retroDto) {

        retroMemberRepo.save(RetroMember.builder()
                .retroId(retroId)
                .memberId(retroDto.getCreateMemberId())
                .auth(RetroAuth.ADMIN)
                .build());

        List<RetroMember> retroMembers = retroDto.getJoinMemberIds().stream()
                .map(memberId -> RetroMember.builder()
                        .retroId(retroId)
                        .memberId(memberId)
                        .auth(RetroAuth.USER).build())
                .collect(Collectors.toList());

        retroMemberRepo.saveAll(retroMembers);
    }
}
