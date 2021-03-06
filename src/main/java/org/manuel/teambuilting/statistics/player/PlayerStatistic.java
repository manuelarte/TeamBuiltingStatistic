package org.manuel.teambuilting.statistics.player;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.manuel.teambuilting.statistics.StatisticObject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author manuel.doncel.martos
 * @since 27-12-2016
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Entity
@Audited
@AllArgsConstructor
@NoArgsConstructor
@Table
public class PlayerStatistic implements StatisticObject {

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	private String playerId;

	@NotNull
	@Min(value=0)
	private long timesVisited;
}
