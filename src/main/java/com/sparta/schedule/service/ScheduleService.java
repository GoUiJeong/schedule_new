package com.sparta.schedule.service;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponsDto;
import com.sparta.schedule.entity.Schedule;
import com.sparta.schedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public ScheduleResponsDto createSchedule(ScheduleRequestDto requestDto) {
        // RequestDto -> Entity
        Schedule schedule = new Schedule(requestDto);
        // DB 저장
        Schedule saveSchedule = scheduleRepository.save(schedule);
        // Entity -> ResponseDto
        ScheduleResponsDto scheduleResponseDto = new ScheduleResponsDto(saveSchedule);
        return scheduleResponseDto;
    }

    public List<ScheduleResponsDto> getSchedules() {
        //DB조회
        return scheduleRepository.findAllByOrderByModifiedAtDesc().stream().map(ScheduleResponsDto::new).toList();
    }

    public List<ScheduleResponsDto> getSchedulesByKeyword(String keyword) {
        return scheduleRepository.findAllByContentsContainsOrderByModifiedAtDesc(keyword).stream().map(ScheduleResponsDto::new).toList();
    }

    @Transactional
    public Long updateSchedule(Long id, ScheduleRequestDto requestDto) {
        // 해당 메모가 DB에 존재하는지 확인
        Schedule schedule = findSchedule(id);
        // memo 내용 수정
        schedule.update(requestDto);
        return id;
    }

    public Long deleteSchedule(Long id) {
        // 해당 메모가 DB에 존재하는지 확인
        Schedule schedule = findSchedule(id);
        // memo 삭제
        scheduleRepository.delete(schedule);
        return id;
    }

    private Schedule findSchedule(Long id) {
        return scheduleRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 메모는 존재하지 않습니다.")
        );
    }

}
