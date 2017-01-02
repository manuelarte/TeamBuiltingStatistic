package org.manuel.teambuilting.statistics.messages;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Event Message to show that a player was deleted
 *
 * @author Manuel Doncel Martos
 * @since 07/12/2016.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize
@Data
@AllArgsConstructor
public class PlayerDeletedMessage {

    @NotNull
    private final Player player;

    @NotNull
    private final String userId;

    @NotNull
    private final Date date;
}
