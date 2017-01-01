package org.manuel.teambuilting.statistics;

/**
 * @author Manuel Doncel Martos
 * @since 01/01/2017.
 */
public interface TeamIdDependent<T> {

    /**
     * Find the entity/entities whose team id matches the input parameter
     * @param teamId
     * @return
     */
    T findByTeamId(String teamId);
}
