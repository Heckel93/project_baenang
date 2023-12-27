package com.bn.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bn.model.ContentVo;
import com.bn.service.TourInfoService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
public class TourInfoController {
	
	@Autowired
	private TourInfoService ti;
	
	private ContentVo vo;
	
	//plan에서 검색된 관광지 목록의 info버튼 클릭시 요청을 받도록 설계 
	@RequestMapping("/tourInfo")
	public String tourInfo(@RequestParam String contentid, Model model) {
		
		vo=null;
		vo=ti.getCityData(contentid);
		
		//select overview from content where contentid=${contentid} 의 반환값(요청된 contentid의 overview이 있는지 확인하는 함수)
		String exist = ti.existOverview(contentid);
		if(exist==null) {
			System.out.println("Overview is empty!! I'll bring it from API");
			overviewfill(contentid);
		}else {
			System.out.println("Overview data is already exist!! I'll show you Tourinfo from DB");
		}
		model.addAttribute("vo",vo);
		log.info("Last : "+vo);
		
		return "tourInfo";
	}
	/*
	//TourInfo.jsp 페이지가 호출된 직후 실행되는 function(contentid)을 통해 /existOverview?contentid=#{contentid} 의 형식으로 파라미터를 받아
	@ResponseBody
	@RequestMapping("/existOverview")
	private String existOverview(String contentid) {
		String exist = ti.existOverview(contentid);
		if(exist==null) {
			overviewfill(contentid);
		}
		System.out.println("Overview data is already exist!! I'll show you Tourinfo");
		return "";
	}
	*/

	private String overviewfill(String contentid) {
		
		String result="";
		
		try {
			String pkey="g+INH4ICelRYTwvUPjujUIt/O1i9eSZAmhiCR9xJLT3v4P4aNkdXnRnDCkDGMKIdpXvJPsGJ9I5HTG6T2lmjkg==";
			String key = URLEncoder.encode(pkey, "UTF-8");
			String apiURL="https://apis.data.go.kr/B551011/KorService1/detailCommon1?MobileOS=ETC&MobileApp=2clipse&_type=json&contentId="+contentid+"&defaultYN=Y&firstImageYN=Y&areacodeYN=Y&catcodeYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y&serviceKey="+key;
			URL url = new URL(apiURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setUseCaches(false);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestProperty("Content-Type", "text/plain");
			int responseCode = conn.getResponseCode();
			
			if (responseCode == 200) {
				BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),StandardCharsets.UTF_8));
				String inputLine;
				StringBuilder response = new StringBuilder();
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				
				JsonObject jsonResponse = JsonParser.parseString(response.toString()).getAsJsonObject();
				JsonObject responseHeader = jsonResponse.getAsJsonObject("response").getAsJsonObject("header");
				log.info(responseHeader.get("resultCode").toString());
				if ("0000".equals(responseHeader.get("resultCode").getAsString())) {
					JsonObject responseBody = jsonResponse.getAsJsonObject("response").getAsJsonObject("body");
					JsonArray items = responseBody.getAsJsonObject("items").getAsJsonArray("item");
					if (items.size() > 0) {
						for (int i = 0; i < items.size(); i++) {
							JsonObject item = items.get(i).getAsJsonObject();
							String overview = item.get("overview").getAsString();
							
							vo.setOverview(overview);
							
							log.info("vo.setOverview(overview) : "+vo);
							
							result = vo.toString();
							
							if(overview!=null) {
								int n= ti.insertOverview(vo);
								System.out.println("INSERT 성공:1, 실패:0 >> : "+n);
							}else {
								System.out.println("�����Ͱ� �����ϴ�.");
							}
						}
					} else {
						System.out.println("�����Ͱ� �����ϴ�.");
					}
				} else {
					System.out.println("API ��û�� �����߽��ϴ�.");
				}
			} else {
				System.out.println("API ��û�� �����߽��ϴ�. ���� �ڵ�: " + responseCode);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	return result;
	}
	
}