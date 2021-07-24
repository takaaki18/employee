package jp.co.ttt.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.ttt.employee.repository.EmployeeRepository;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeRepository repository;

	// 全件検索
	@RequestMapping(path = "/findAll")
	public String showemployeeList(Model model) {

		model.addAttribute("employees", repository.findAll());
		return "employee/employee_list";
	}

	// 主キー(従業員番号)で検索
	@RequestMapping(path = "/getOne/{id}")
	public String showEmployee(@PathVariable int id, Model model) {

		model.addAttribute("employees", repository.getOne(id));
		return "employee/employee_list";
	}

	// 部署番号で検索
	@RequestMapping(path = "/findByDeptNo/{deptNo}")
	public String showEmpoloyeeListByDeptNo(@PathVariable int deptNo, Model model) {

		System.out.println(repository.findByDeptNo(deptNo));
		model.addAttribute("employees", repository.findByDeptNo(deptNo));
		return "employee/employee_list";
	}

	// 名前＆部署番号で検索
	@RequestMapping(path = "/findByNameAndDeptNo/{name}/{deptNo}")
	public String showEmpoloyeeListByNameAndDeptNo(@PathVariable String name, @PathVariable int deptNo,
			Model model) {

		model.addAttribute("employees", repository.findByNameAndDeptNo(name, deptNo));
		return "employee/employee_list";
	}

	// 名前の部分検索を行う
	@RequestMapping(path = "/findByNameLike/{name}")
	public String showEmployeeListNameLike(@PathVariable String name, Model model) {

		model.addAttribute("employees", repository.findByNameLike("%" + name + "%"));
		return "employee/employee_list";
	}
}
