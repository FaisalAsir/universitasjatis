package com.universitasjatis.universitasjatis.entity;

import com.universitasjatis.universitasjatis.enums.JenisKelamin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "MAHASISWA")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mahasiswa extends BaseEntity{

    @Column(name="NPM", unique = true)
    private String npm;
    @Column(name="NAMA")
    private String nama;
    @Column(name="ALAMAT")
    private String alamat;
    @Column(name="JENISKELAMIN")
    @Enumerated(EnumType.STRING)
    private JenisKelamin jenisKelamin;
    @Column(name="TEMPATLAHIR")
    private String tempatLahir;
    @Column(name="TANGGALLAHIR")
    private LocalDate tanggalLahir;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "mahasiswa")
    private List<NilaiMatakuliah> nilaiMatakuliahLs;
}
