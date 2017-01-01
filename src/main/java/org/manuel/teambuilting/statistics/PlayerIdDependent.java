package org.manuel.teambuilting.statistics;

/**
 * @author Manuel Doncel Martos
 * @since 01/01/2017.
 */
public interface PlayerIdDependent<T> {

    /**
     * Find the entity/entities whose player id matches the input parameter
     * @param playerId
     * @return
     */
    T findByPlayerId(String playerId);
}
