package sample.vo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="rolroralra.GROUP")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Group {
	@Id
	@Column(name="GROUP_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="GROUP_NAME")
	private String name;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="group")
	private List<Member> members;
}
