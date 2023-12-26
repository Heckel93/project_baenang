package com.bn.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bn.model.ContentVo;
import com.bn.service.MainServiceImpl;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
public class MainController {

	@Inject
	private MainServiceImpl msi;
	
	@GetMapping("/sample")
	public String sample() {
		return "sample";
	}
	
	@RequestMapping("/main")
	public String onMain() {
		
		return "main";
	}
	
	@RequestMapping("/user/main")
	public String goMain() {
		
		return "main";
	}
	
	@RequestMapping(value = "/city")
	public String city() {
		return "city";
	}
	
	@ResponseBody
	@GetMapping("/rec")
	public ModelMap rec() {
		ModelMap map=new ModelMap();
		String contentid="1797757";
		String title=msi.rec(contentid);
		map.addAttribute("title", title);
		return map;
	}


	@ResponseBody
	 @GetMapping("/msearch") 
	 public String[] search(@RequestParam String keyword) {
		List<ContentVo> result = null;
		
		try {
			result = msi.search(keyword);
		}catch(Exception e) {
			
		}
		 
		 System.out.println("Controller : "+result);
		 
		 List<ContentVo> searchList = result; // 받아온 데이터를 searchList에 할당
		
		String[] cityNameList = new String[5]; //jsp에 .append로 넣을 수 있게 스트링타입의 배열을 생성 
		
		 for (int i = 0; i < searchList.size(); i++) {
		     ContentVo content = searchList.get(i); 
		     String cityName = content.getCITYNAME(); // Vo클래스의 getter 메서드를 사용하여 CITYNAME의 값만 추출 
		     cityNameList[i]=cityName;
		     System.out.println("cityNameList["+i+"] : " + cityNameList[i]);
		 }
		 
		 return cityNameList;
	 }
	 
	 /*
	 @ResponseBody
	 @GetMapping("/msearch") 
	 public List<ContentVo> search(@RequestParam String keyword) {
		List<ContentVo> result = null;
		
		try {
			result = msi.search(keyword);
		}catch(Exception e) {
			
		}
		 
		 System.out.println(result);
		 
		 List<ContentVo> searchList = result; // 받아온 데이터를 searchList에 할당

		 for (ContentVo content : searchList) {
		     String title = content.getTitle();
		     System.out.println("title: " + title);
		 }
		 for (ContentVo content : searchList) {
			 String contentid = content.getContentid();
			 System.out.println("contentid: " + contentid);
		 }
		 for (ContentVo content : searchList) {
			 String addr = content.getAddr();
			 System.out.println("addr: " + addr);
		 }
		 return searchList;
	 }
	 */
	 
	
	/*
	 * @ResponseBody
	 * 
	 * @GetMapping("/search") public String search(@RequestParam String keyword) {
	 * 
	 * return msi.search(keyword); }
	 */
	
}
