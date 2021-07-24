package jp.co.ttt.employee.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// name属性はテーブル名
@Entity
@Table(name = "employee")
public class Employee {

	// 主キーの列は@Id
	@Id
	private Integer id;

	// 主キー以外の列は@Column
	@Column
	private String name;

	// 主キー以外の列は@Column
	@Column
	private Integer deptNo;

	// Getter, Setter
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(Integer deptNo) {
		this.deptNo = deptNo;
	}
}
