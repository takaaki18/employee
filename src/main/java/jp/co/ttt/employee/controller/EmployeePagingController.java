package jp.co.ttt.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.ttt.employee.entity.Employee;
import jp.co.ttt.employee.repository.EmployeeRepository;

@Controller
public class EmployeePagingController {

	@Autowired
	EmployeeRepository repository;

	@RequestMapping("/findAllPaging")
	// 引数Pageableの中に「ページ番号」、「1ページあたりの表示件数」などの設定情報が含まれる。
	// ConfigurationクラスでPageable型の引数に関する情報を設定した場合、それらの設定値が含まれる
	// ページ番号「0」と1ページあたりの表示件数「10」
	public String showEmployeePagingList(Model model, Pageable pageable) {

		// 従業員情報を検索(ページ情報つきの検索)
		Page<Employee> pageList = repository.findAll(pageable);

		// 検索結果を保存するためのリストを用意(レコード情報のみを取得)
		// ビューに渡す際、Page型の変数をそのまま渡しても実装可能だが、記載が複雑になるのでレコード情報だけを別にわたすことで可読性があがる。
		List<Employee> employeeList = pageList.getContent();

		// コンソール確認用
		for(Employee employee : employeeList) {
			System.out.println(employee);
		}

		// 従業員情報をリクエストスコープに保存
		model.addAttribute("pages", pageList);
		model.addAttribute("employees", employeeList);

		return "employee/employee_paging_list";
	}
}
