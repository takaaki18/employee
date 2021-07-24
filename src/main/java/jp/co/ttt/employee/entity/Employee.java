package jp.co.ttt.employee.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

// name属性はテーブル名
@Data
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

}
