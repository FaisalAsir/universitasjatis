package com.universitasjatis.universitasjatis.enums;

import lombok.Getter;

@Getter
public enum JenisKelamin {
    P("PEREMPUAN"),L("LAKI-LAKI");

    JenisKelamin(String desc) {
        this.description =desc;
    }

    private String description;

}
