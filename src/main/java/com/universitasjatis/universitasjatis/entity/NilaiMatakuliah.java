package com.universitasjatis.universitasjatis.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "NILAI_MATAKULIAH")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NilaiMatakuliah extends BaseEntity{
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "IDMAHASISWA")
    private Mahasiswa mahasiswa;
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "IDDOSEN")
    private Dosen dosen;
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "IDMATAKULIAH")
    private Matakuliah matakuliah;
    @Column(name = "NILAI")
    private Long nilai;
}
