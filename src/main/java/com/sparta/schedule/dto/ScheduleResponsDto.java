package com.sparta.schedule.dto;

import com.sparta.schedule.entity.Schedule;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ScheduleResponsDto {
    private Long id;
    private String name;
    private String title;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public ScheduleResponsDto(Schedule entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.title = entity.getTitle();
        this.contents = entity.getContents();
        this.createdAt = entity.getCreatedAt();
        this.modifiedAt = entity.getModifiedAt();
    }
}