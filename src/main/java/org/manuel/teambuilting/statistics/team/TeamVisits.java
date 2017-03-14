package org.manuel.teambuilting.statistics.team;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.envers.Audited;
import org.manuel.teambuilting.statistics.VisitsObject;

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
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamVisits implements VisitsObject {

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	private String userId;

	@NotNull
	private String teamId;

	@NotNull
	@Past
	private Date when;
}
