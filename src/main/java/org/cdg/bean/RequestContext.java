package org.cdg.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestContext {
    private Map<String,String> http;
}
