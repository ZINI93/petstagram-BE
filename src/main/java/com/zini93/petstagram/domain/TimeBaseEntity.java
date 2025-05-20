package com.zini93.petstagram.domain;

import jakarta.persistence.Column;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

public class TimeBaseEntity {

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime crated_at;

    @LastModifiedDate
    @Column(name = "last_modified_at")
    private LocalDateTime lastModifiedAt;

}
