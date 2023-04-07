package com.nudt.security.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;

    public SysRole(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    private List<SysPermission> permissions;

}
