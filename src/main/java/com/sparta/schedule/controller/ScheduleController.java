package com.sparta.schedule.controller;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponsDto;
import com.sparta.schedule.service.ScheduleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    //작성
    @PostMapping("/write")
    public ScheduleResponsDto createSchedule(@RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.createSchedule(requestDto);
    }

    //읽기
    @GetMapping("/main")
    public List<ScheduleResponsDto> getSchedule() {
        return scheduleService.getSchedules();
    }

    //수정
    @PutMapping("/{id}")
    public Long updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.updateSchedule(id, requestDto);
    }

    //삭제
    @DeleteMapping("/{id}")
    public Long deleteMemo(@PathVariable Long id) {
        return scheduleService.deleteSchedule(id);
    }


}
