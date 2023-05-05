package com.manage.reactive.apis.personapis.domain.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class AuditEntity {

    @CreatedDate
    private LocalDateTime cretDt;

    @LastModifiedDate
    private LocalDateTime updDt;

    @CreatedBy
    private String cretId;

    @LastModifiedBy
    private String updId;
    
}
