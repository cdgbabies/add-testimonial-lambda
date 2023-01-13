package org.cdg.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString

public class Testimonial {

    private UUID id;

    private String author;
    private String testimonial;

    private boolean approved;
}
