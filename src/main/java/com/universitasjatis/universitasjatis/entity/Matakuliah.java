package com.universitasjatis.universitasjatis.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "MATAKULIAH")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Matakuliah extends BaseEntity {
    @JoinColumn(name = "NAMAMATAKULIAH")
    private String namaMatakuliah;
    @JoinColumn(name = "SKS")
    private Integer sks;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "matakuliah")
    private List<NilaiMatakuliah> nilaiMatakuliahLs;
}
