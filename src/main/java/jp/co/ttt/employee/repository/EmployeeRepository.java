package jp.co.ttt.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.ttt.employee.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

	// 部署番号検索
	List<Employee> findByDeptNo(Integer deptNo);

	// 従業員名＆部署番号検索
	List<Employee> findByNameAndDeptNo(String name, Integer deptNo);

	// 従業員名部分検索
	List<Employee> findByNameLike(String name);

}
