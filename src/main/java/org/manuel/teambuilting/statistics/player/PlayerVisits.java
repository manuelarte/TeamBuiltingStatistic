package org.manuel.teambuilting.statistics.player;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.manuel.teambuilting.statistics.VisitsObject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

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
public class PlayerVisits implements VisitsObject {

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	private String userId;

	@NotNull
	private String playerId;

	@NotNull
	private Date when;
}
