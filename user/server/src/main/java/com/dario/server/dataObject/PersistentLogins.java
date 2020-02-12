package com.dario.server.dataObject;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "persistent_logins")
public class PersistentLogins {

    @Id
    private String series;

    private String username;

    private String token;

    private Date last_used;

}
