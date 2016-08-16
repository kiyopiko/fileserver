package org.creativebee.fileserver.folder.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.creativebee.fileserver.folder.bean.FileBean;
import org.creativebee.fileserver.folder.bean.TestTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Transactional
@RequestMapping("folder")
public class FolderController {

	@Autowired
	protected NamedParameterJdbcTemplate jdbcTemplate;

	@RequestMapping
	public String menu(Model model) {

		List<FileBean> fileList = new ArrayList<>();
		fileList.add(new FileBean(true, "タイトル1", new Timestamp(new Date()
				.getTime())));
		fileList.add(new FileBean(true, "タイトル2", new Timestamp(new Date()
				.getTime())));
		fileList.add(new FileBean(true, "タイトル3", new Timestamp(new Date()
				.getTime())));
		fileList.add(new FileBean(true, "タイトル4", new Timestamp(new Date()
				.getTime())));
		fileList.add(new FileBean(true, "タイトル5", new Timestamp(new Date()
				.getTime())));

		model.addAttribute("fileList", fileList);

		return "folder";
	}

	@RequestMapping(value = "/switch", params = "schedule")
	public String schedule(Model model) {

		return "schedule";
	}

	@RequestMapping(value = "/switch", params = "scheduleList")
	public String scheduleList(Model model) {

		String sql = "select id, params1 from test_table where id = :id";

		SqlParameterSource param = new MapSqlParameterSource().addValue("id",
				"aaaaaaaaaaaaaaaaaaaa");

		TestTable ret = jdbcTemplate.queryForObject(
				sql,
				param,
				(rs, rownum) -> new TestTable(rs.getString("id"), rs
						.getString("params1")));

		System.out.println("ret:" + ret.getId());

		List<TestTable> list = new ArrayList<>();

		list.add(ret);

		model.addAttribute("hogeList", list);

		String hoge = new Date().toString();

		String updateSql = "insert into test_table (id, params1) values ('"
				+ hoge + "','hoge3')";

		jdbcTemplate.update(updateSql, param);

		return "scheduleList";
	}

	@RequestMapping(value = "/switch", params = "password")
	public String password(Model model) {

		return "password";
	}

	@RequestMapping(value = "/switch", params = "holiday")
	public String holiday(Model model) {

		return "holiday";
	}

	@RequestMapping(value = "/switch", params = "holidayList")
	public String holidayList(Model model) {

		return "holidayList";
	}
}
