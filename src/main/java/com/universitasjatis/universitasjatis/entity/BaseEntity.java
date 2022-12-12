package com.universitasjatis.universitasjatis.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@MappedSuperclass
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
abstract class BaseEntity {
    @SequenceGenerator(name = "seq", allocationSize = 1, sequenceName = "MAHASISWA_S")
    @GeneratedValue(generator = "seq",strategy = GenerationType.SEQUENCE)
    @Id
    @Column(name="ID")
    private Long id;

    @Column(name="CREATEDDATE")
    @CreatedDate
    private LocalDate createdDate;
    @Column(name="CREATEDBY")
    @CreatedBy
    private String createdBy;
    @Column(name="LASTUPDATEDDATE")
    @LastModifiedDate
    private LocalDate lastUpdatedDate;
    @Column(name="LASTUPDATEDBY")
    @LastModifiedBy
    private String lastUpdatedBy;
}


