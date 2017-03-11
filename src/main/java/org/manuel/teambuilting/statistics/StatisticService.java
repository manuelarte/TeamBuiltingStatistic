package org.manuel.teambuilting.statistics;

/**
 * @author manuel.doncel.martos
 * @since 20-12-2016
 */
public interface StatisticService<T extends StatisticObject> {

	T updateTimesVisited(String objectId);

}
