package org.creativebee.fileserver.index.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Transactional
@RequestMapping("index")
public class IndexController {

	@RequestMapping
	public String showIndex() {

		return "index";

	}
}
