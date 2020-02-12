package com.dario.server.dataObject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "authorities")
public class Authorities {

    @Id
    private String username;

    private String authority;
}
