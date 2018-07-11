package com.globallogic.dashboard.service;

import com.globallogic.dashboard.model.Member;
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

@Service
public class LoadingServiceImpl implements LoadingService {
    private static final Logger log = LoggerFactory.getLogger(LoadingServiceImpl.class);
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private VacationRepository vacationRepository;
    @Autowired
    private DataLoader dataLoader;

    @Override
    public void uploadDataAndPersist() {
        try {
            List<MemberCreateDto> memberCreateDtos = dataLoader.loadData();
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
            log.error("Error while uploading.", e);
        }
    }
    @Override
    public void uploadVacationDataAndPersist() {

        try {
            List<VacationCreateDto>vacationCreateDtos = dataLoader.loadVacationData();
            for (VacationCreateDto vacationCreateDto: vacationCreateDtos){

                Vacation vacation = new Vacation();
                vacation.setStart(vacationCreateDto.getStart());
                vacation.setEnd(vacationCreateDto.getEnd());
                vacation.setMember((Member) vacationRepository.findByMember_Name(vacationCreateDto.getMemberName()));
                System.out.println(vacation);
                vacationRepository.save(vacation);

            }
        }catch (Exception e){
            log.info("Error while uploading.", e);
        }

    }

}
