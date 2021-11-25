package org.elsys.springbootcalculator;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record Operation(@JsonProperty String operation, @JsonProperty("args") List<String> arguments) {
}
