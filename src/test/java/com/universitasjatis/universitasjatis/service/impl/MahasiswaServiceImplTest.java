package com.universitasjatis.universitasjatis.service.impl;

import com.universitasjatis.universitasjatis.dto.MahasiswaReqDto;
import com.universitasjatis.universitasjatis.entity.Mahasiswa;
import com.universitasjatis.universitasjatis.exceptionHandler.DataNotFoundException;
import com.universitasjatis.universitasjatis.exceptionHandler.JatisExeption;
import com.universitasjatis.universitasjatis.repository.MahasiswaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MahasiswaServiceImplTest {

    @InjectMocks
    MahasiswaServiceImpl mahasiswaServicel;

    @Mock
    private MahasiswaRepository mahasiswaRepository;


    String npm = "123";
    @Test
    @DisplayName("get mahasiswa by npm Success")
    void getMahasiswaDto() {
//        Given
        given(mahasiswaRepository.findByNpm(npm)).willReturn(Optional.ofNullable(Mahasiswa.builder().npm(npm).build()));
//        when
        MahasiswaReqDto mahasiswaReqDto = mahasiswaServicel.getMahasiswaDto(npm);
//        Then
        assertThat(mahasiswaReqDto).isNotNull();
        assertThat(mahasiswaReqDto.getNpm()).isNotBlank().isEqualTo(npm);
    }

    @Test
    @DisplayName("get mahasiswa by npm Not found")
    void getMahasiswaDtoThrow() {
        //        Given
        given(mahasiswaRepository.findByNpm(anyString())).willReturn(Optional.empty());
//        Then
        assertThatThrownBy(() -> mahasiswaServicel.getMahasiswaDto(npm))
                .isInstanceOf(DataNotFoundException.class)
                .hasMessageContaining("Data NotFound");
    }

    @Test
    @DisplayName("get all mahasiswa by npm Success")
    void getAllMahasiswaReqDtos() {
//        given
        given(mahasiswaRepository.findAll()).willReturn(Collections.singletonList(Mahasiswa.builder().npm(npm).build()));
//        when
        List<MahasiswaReqDto> mahasiswas = mahasiswaServicel.getAllMahasiswaReqDtos();
//        Then
        assertThat(mahasiswas).isNotEmpty().hasSize(1);
        assertThat(mahasiswas.get(0).getNpm()).isNotBlank().isEqualTo(npm);
    }

    @Test
    @DisplayName("save mahasiswa success")
    void saveMahasiswaReqDtoSuccess() {
//        Given
        MahasiswaReqDto mahasiswaReqDto = MahasiswaReqDto.builder().npm(npm).build();
        given(mahasiswaRepository.findByNpm(anyString())).willReturn(Optional.empty());
//        When
        MahasiswaReqDto mahasiswaReqDtoRs = mahasiswaServicel.saveMahasiswaReqDto(mahasiswaReqDto);

        assertThat(mahasiswaReqDtoRs).isEqualTo(mahasiswaReqDto);
        verify(mahasiswaRepository,times(1)).save(any(Mahasiswa.class));
    }

    @Test
    @DisplayName("save mahasiswa existing gagal")
    void saveMahasiswaReqDtoThrow() {
//        Given
        MahasiswaReqDto mahasiswaReqDto = MahasiswaReqDto.builder().npm(npm).build();
        given(mahasiswaRepository.findByNpm(anyString())).willReturn(Optional.ofNullable(Mahasiswa.builder().npm(npm).build()));
//        When
        assertThatThrownBy(() -> mahasiswaServicel.saveMahasiswaReqDto(mahasiswaReqDto)).isInstanceOf(JatisExeption.class).hasMessageContaining("Mahasiswa sudah terdaftar");
        verify(mahasiswaRepository,times(0)).save(any(Mahasiswa.class));
    }

    @Test
    @DisplayName("update mahasiswa success")
    void updateMahasiswaReqDtosuccess() {
        //        Given
        MahasiswaReqDto mahasiswaReqDto = MahasiswaReqDto.builder().npm(npm).build();
        given(mahasiswaRepository.findByNpm(anyString())).willReturn(Optional.ofNullable(Mahasiswa.builder().npm(npm).build()));
//        when
        MahasiswaReqDto mahasiswaReqDtoRs = mahasiswaServicel.updateMahasiswaReqDto(mahasiswaReqDto);
//        Then
        assertThat(mahasiswaReqDtoRs).isNotNull().isEqualTo(mahasiswaReqDto);

        verify(mahasiswaRepository,times(1)).save(any(Mahasiswa.class));
    }

    @Test
    @DisplayName("save mahasiswa gagal data tidak ditemukan")
    void updateMahasiswaReqDtoThrow() {
        //        Given
        MahasiswaReqDto mahasiswaReqDto = MahasiswaReqDto.builder().npm(npm).build();
        given(mahasiswaRepository.findByNpm(anyString())).willReturn(Optional.empty());
//        when
        assertThatThrownBy(() -> mahasiswaServicel.updateMahasiswaReqDto(mahasiswaReqDto))
                .isInstanceOf(DataNotFoundException.class)
                .hasMessageContaining("Data NotFound");
        verify(mahasiswaRepository,times(0)).save(any(Mahasiswa.class));
    }

    @Test
    @DisplayName("delete mahasiswa success")
    void deleteMahasiswaReqDtoSucess() {
        //        Given
        given(mahasiswaRepository.findByNpm(anyString())).willReturn(Optional.ofNullable(Mahasiswa.builder().npm(npm).build()));
//        when
        mahasiswaServicel.deleteMahasiswaReqDto(npm);
//        Then
        verify(mahasiswaRepository,times(1)).delete(any(Mahasiswa.class));
    }

    @Test
    @DisplayName("delete mahasiswa gagal data tidak ditemukan")
    void deleteMahasiswaReqDtoThrow() {
        //        Given
        given(mahasiswaRepository.findByNpm(anyString())).willReturn(Optional.empty());
//        when
        assertThatThrownBy(() -> mahasiswaServicel.deleteMahasiswaReqDto(npm))
                .isInstanceOf(DataNotFoundException.class)
                .hasMessageContaining("Data NotFound");
        verify(mahasiswaRepository,times(0)).delete(any(Mahasiswa.class));
    }
}