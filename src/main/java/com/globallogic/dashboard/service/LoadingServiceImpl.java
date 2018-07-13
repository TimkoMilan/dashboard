package com.globallogic.dashboard.service;

import com.globallogic.dashboard.model.Member;
import com.globallogic.dashboard.model.SprintData;
import com.globallogic.dashboard.model.Vacation;
import com.globallogic.dashboard.repository.MemberRepository;
import com.globallogic.dashboard.repository.VacationRepository;
import com.globallogic.dashboard.to.MemberCreateDto;
import com.globallogic.dashboard.to.VacationCreateDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class LoadingServiceImpl implements LoadingService {
    private static final Logger log = LoggerFactory.getLogger(LoadingServiceImpl.class);

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private VacationRepository vacationRepository;
   /* @Autowired
    private DataLoader dataLoader;*/
   @Autowired
   private GoogleDataLoader googleDataLoader;
   @Autowired
   private VacationDataLoader vacationDataLoader;



    @Override
    public void uploadDataAndPersist() {
        try {
            List<MemberCreateDto> memberCreateDtos = googleDataLoader.loadData();
            log.info("Found {} members... persisting", memberCreateDtos.size());
            for (MemberCreateDto memberCreateDto : memberCreateDtos) {
                Member member = new Member();
                member.setName(memberCreateDto.getName());
                member.setBillingValue(memberCreateDto.getBillingValue());
                member.setPosition(memberCreateDto.getPosition());
                member.setFocus(memberCreateDto.getFocus());
                memberRepository.save(member);
            }
        } catch (Exception e) {
            log.error("Error while uploading a member .", e);
        }
    }

    @Override
    public void uploadVacationDataAndPersist() {
        try {
            List<VacationCreateDto> vacationCreateDtos = vacationDataLoader.loadVacationData();
            for (VacationCreateDto vacationCreateDto: vacationCreateDtos){
                Vacation v = createVacation(vacationCreateDto);
                vacationRepository.save(v);
            }
        }catch (Exception e){
            log.info("Error while uploading a vacation.", e);
        }

    }

    private Vacation createVacation(VacationCreateDto dto){
        Vacation vacation = new Vacation();
        Member member = memberRepository.findMemberByNameIsLike(dto.getMemberName());
        vacation.setStart(dto.getStart());
        vacation.setEnd(dto.getEnd());
        vacation.setMonth(dto.getMonth());
        vacation.setMember(member);
        return vacation;
    }

}
