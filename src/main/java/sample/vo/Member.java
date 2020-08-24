package sample.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="rolroralra.MEMBER")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	@Id
	@Column(name="MEMBER_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="MEMBER_NAME")
	private String name;
	
	@Column(name="MEMBER_DETAIL")
	private String detail;

	
	@ManyToOne
	@JoinColumn(name="GROUP_ID")
	@ToString.Exclude
	private Group group;
	
}
