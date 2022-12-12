package com.universitasjatis.universitasjatis.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.universitasjatis.universitasjatis.enums.JenisKelamin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MahasiswaReqDto {
    @NotBlank(message = "NPM wajib diisi")
    private String npm;
    @NotBlank(message = "nama wajib diisi")
    private String nama;
    @NotBlank(message = "alamat wajib diisi")
    private String alamat;
    @NotNull(message = "jenisKelamin wajib diisi")
    private JenisKelamin jenisKelamin; //TODO: make custome constraint for enum GENDER
    @NotBlank(message = "tempat lahir wajib diisi")
    private String tempatLahir;
    @JsonFormat(pattern = "dd-MM-yyyy")
    @JsonDeserialize(using = DateFormatSerializer.class)
    private LocalDate tanggalLahir;
}
