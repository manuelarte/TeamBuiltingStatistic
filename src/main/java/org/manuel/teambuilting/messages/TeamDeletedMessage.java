package org.manuel.teambuilting.messages;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.manuel.teambuilting.dtos.Team;

/**
 * @author Manuel Doncel Martos
 * @since 11/01/2017.
 */
@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize
public class TeamDeletedMessage {

    @NotNull
    private final Team team;

    private final String userId;

    @NotNull
    private Date date;
}
