package org.manuel.teambuilting.statistics.messages;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Manuel Doncel Martos
 * @since 07/12/2016.
 */
@Data
@AllArgsConstructor
public class TeamChangedMessage {

    private final Team team;
    private final String changeType;
}
