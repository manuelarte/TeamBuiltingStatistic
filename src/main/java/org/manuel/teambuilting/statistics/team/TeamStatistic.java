package org.manuel.teambuilting.statistics.team;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.manuel.teambuilting.statistics.StatisticObject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author manuel.doncel.martos
 * @since 27-12-2016
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Entity
@Audited
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamStatistic implements StatisticObject {

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	private String teamId;

	@NotNull
	@Min(value=0)
	private long timesVisited;
}
