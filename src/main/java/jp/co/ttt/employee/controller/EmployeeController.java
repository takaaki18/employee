package jp.co.ttt.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.ttt.employee.entity.Employee;
import jp.co.ttt.employee.form.EmployeeForm;
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

	// 主キー(従業員番号)検索
	@RequestMapping(path = "/getOne/{id}")
	public String showEmployee(@PathVariable int id, Model model) {

		model.addAttribute("employees", repository.getOne(id));
		return "employee/employee_list";
	}

	// 部署番号検索を行う
	@RequestMapping(path = "/findByDeptNo/{deptNo}")
	public String showEmpoloyeeListByDeptNo(@PathVariable int deptNo, Model model) {

		System.out.println(repository.findByDeptNo(deptNo));
		model.addAttribute("employees", repository.findByDeptNo(deptNo));
		return "employee/employee_list";
	}

	// 従業員名＆部署番号検索を行う
	@RequestMapping(path = "/findByNameAndDeptNo/{name}/{deptNo}")
	public String showEmpoloyeeListByNameAndDeptNo(@PathVariable String name, @PathVariable int deptNo,
			Model model) {

		model.addAttribute("employees", repository.findByNameAndDeptNo(name, deptNo));
		return "employee/employee_list";
	}

	// 従業員名部分検索を行う
	@RequestMapping(path = "/findByNameLike/{name}")
	public String showEmployeeListNameLike(@PathVariable String name, Model model) {

		model.addAttribute("employees", repository.findByNameLike("%" + name + "%"));
		return "employee/employee_list";
	}

	// 入力画面に遷移する
	@RequestMapping(path = "/create/input")
	public String createInput() {

		return "employee/create_input";
	}

	// 商品を登録する
	@RequestMapping(path = "/create/complete", method = RequestMethod.POST)
	public String createComplete(EmployeeForm form) {

		Employee employee = new Employee();
		employee.setId(form.getId());
		employee.setName(form.getName());
		employee.setDeptNo(form.getDeptNo());
		repository.save(employee);

		return "redirect:/getOne/" + employee.getId();
	}

	// 更新内容入力画面に遷移する
	@RequestMapping(path = "/update/input/{id}")
	public String updateInput(@PathVariable int id, Model model) {

		model.addAttribute("employee", repository.getOne(id));
		return "employee/update_input";
	}

	// 商品を更新する
	@RequestMapping(path = "/update/complete/{id}", method = RequestMethod.POST)
	public String updateComplete(@PathVariable int id, EmployeeForm form) {

		Employee employee = repository.getOne(id);
		employee.setId(form.getId());
		employee.setName(form.getName());
		employee.setDeptNo(form.getDeptNo());
		repository.save(employee);
		return "redirect:/getOne/" + employee.getId();
	}

	// 削除画面に遷移する
	@RequestMapping(path = "/delete/input")
	public String deleteInput() {

		return "employee/delete_input";
	}

	// 商品を削除する
	@RequestMapping(path = "/delete/complete", method = RequestMethod.POST)
	public String deleteComplete(EmployeeForm form) {

		repository.deleteById(form.getId());
		return "redirect:/findAll";
	}

}
