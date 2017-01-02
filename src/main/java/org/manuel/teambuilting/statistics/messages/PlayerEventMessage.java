package org.manuel.teambuilting.statistics.messages;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Manuel Doncel Martos
 * @since 07/12/2016.
 */
@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize
public class PlayerEventMessage {

    @NotNull
    private final Player player;

    private final String userId;

    @NotNull
    private final String eventType;

    @NotNull
    private Date date;
}
