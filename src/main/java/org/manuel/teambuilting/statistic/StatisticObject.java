package org.manuel.teambuilting.statistic;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author manuel.doncel.martos
 * @since 20-12-2016
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticObject<T> {

	@NotNull
	private T data;

	@NotNull
	@Min(value=0)
	private long numberOfHits;

}
