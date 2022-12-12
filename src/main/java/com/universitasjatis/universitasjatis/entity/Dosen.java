package com.universitasjatis.universitasjatis.entity;

import com.universitasjatis.universitasjatis.enums.JenisKelamin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "DOSEN")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dosen extends BaseEntity {

    @Column(name="NAMA")
    private String nama;
    @Column(name="ALAMAT")
    private String alamat;
    @Column(name="JENISKELAMIN")
    @Enumerated(EnumType.STRING)
    private JenisKelamin jenisKelamin;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "dosen")
    private List<NilaiMatakuliah> nilaiMatakuliahLs;
}
