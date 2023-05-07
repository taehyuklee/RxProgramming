package com.manage.pure.apis.personapis.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain = true)
@MappedSuperclass
@EntityListeners(value = AuditingEntityListener.class)
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
