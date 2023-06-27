package com.spring.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.community.model.CommunityDAO;
import com.community.model.PageDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.member.model.MemberDAO;
import com.member.model.MemberDTO;
import com.play.model.Play_CommentDAO;
import com.play.model.Play_CommentDTO;
import com.play.model.Play_InfoDAO;
import com.play.model.Play_InfoDAOImpl;
import com.play.model.Play_InfoDTO;
import com.play.model.Play_LikeDAO;
import com.play.model.Play_LikeDTO;
import com.point.model.PointDAO;

@Controller
public class PlayController {
	@Autowired
	private CommunityDAO daoCommu;

	@Autowired
	private Play_InfoDAO dao;

	@Autowired
	private Play_InfoDAOImpl daoIm;

	@Autowired
	private Play_LikeDAO likedao;

	@Autowired
	private MemberDAO memdao;

	@Autowired
	private Play_CommentDAO comdao;


	@Autowired
	private PointDAO pDao;

	@RequestMapping("/RandomClickPoint.do")
	public void randomPointDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
	  HttpSession session = request.getSession();
	  PrintWriter out = response.getWriter();

	  String id = (String) session.getAttribute("UserId");

	  System.out.println(id);

	  MemberDTO mDto = this.memdao.getMember(id);

	  System.out.println("mDto >>>" + mDto);

	  // 포인트가 5 미만인 경우 함수 실행하지 않음
	  if (mDto.getPoint() < 5) {
		  response.setStatus(HttpServletResponse.SC_OK);
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    JsonObject jsonResponse = new JsonObject();
		    jsonResponse.addProperty("result", "InsufficientPoints");
		    jsonResponse.addProperty("point", mDto.getPoint());
		    response.getWriter().write(jsonResponse.toString());
		    return;
	  }else {
		  // 포인트 감소 메서드
		  daoCommu.pointDelete(id);

		  // Mypage에 내역 작성
		  Map<String, Object> pointInsert = new HashMap<String, Object>();
		  pointInsert.put("member_id", id);
		  pointInsert.put("point_content", "Random 추천 사용");
		  pointInsert.put("point_sign", "-");
		  pointInsert.put("point_score", 5);
		  pointInsert.put("point_remanet", mDto.getPoint());
		  this.pDao.pointInsert(pointInsert);

		  // 적절한 응답 작성
		  response.setStatus(HttpServletResponse.SC_OK);
		  response.setContentType("application/json");
		  response.setCharacterEncoding("UTF-8");
		  JsonObject jsonResponse = new JsonObject();
		  jsonResponse.addProperty("result", "Success");
		  jsonResponse.addProperty("point", mDto.getPoint());
		  response.getWriter().write(jsonResponse.toString());
	  }




	}


	

	@RequestMapping("play_content.do")
	public String cont(@RequestParam("play_idx") int idx, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String id = (String) request.getSession().getAttribute("UserId");
		if (id != null) {
			MemberDTO mDTO = memdao.getMember(id);
			model.addAttribute("memDTO", mDTO);
		}
		Play_InfoDTO dto = daoIm.playCont(idx);
		List<Play_CommentDTO> rList = comdao.replayList(idx);
		Play_InfoDTO infodto = new Play_InfoDTO();

		int startCount = comdao.startcount(idx);

		System.out.println("스1 >>" + startCount);
		if (startCount == 0) {
			System.out.println("스2 >>" + startCount);
			startCount = 1;
			System.out.println("스3 >>" + startCount);
		}

		int totalcomment = comdao.commentCount(idx);

		model.addAttribute("playCont", dto).addAttribute("replayList", rList).addAttribute("InfoDto", infodto)
				.addAttribute("star", startCount).addAttribute("total", totalcomment);

		return "play/play_content";

	}

	@RequestMapping("play_list.go")
	public String all(Model model, HttpSession session) {
		List<Play_InfoDTO> list = this.dao.getPlayList();

		model.addAttribute("List", list);

		if (session.getAttribute("UserId") != null) {
			String dto = (String) session.getAttribute("UserId");

			List<Integer> like_list = dao.getPlayLikeList(dto);
			model.addAttribute("like_list", like_list);
		}

		return "play/play_list";
	}

	@RequestMapping("SearchResultOk.do")
	public String SearchResultList(HttpServletRequest req, HttpServletResponse rep, Model model) {

		String[] sideList = req.getParameterValues("side_chk");
		String[] locList = req.getParameterValues("loc_chk");
		String[] priceList = req.getParameterValues("price_range");
		List<String> locValues = null;
		List<String> sideValues = null;
		List<String> priceValues = null;

		if (sideList != null) {
			sideValues = Arrays.asList(sideList);
		}

		if (locList != null) {
			locValues = Arrays.asList(locList);
		}

		if (priceList != null) {
			priceValues = Arrays.asList(priceList);
		}

		System.out.println("locValues" + locValues);
		System.out.println("sideValues" + sideValues);
		System.out.println("priceValues" + priceValues);

		List<Play_InfoDTO> list = this.dao.getPlaySearchList(locValues, sideValues, priceValues);

		model.addAttribute("PlaySearchList", list);

		return "play/play_result";
	}

	@RequestMapping("DistanceSearchResultOk.do")
	public String SearchDistanceResultList(HttpServletRequest req, HttpServletResponse rep, Model model) {

		String[] distanceList = req.getParameterValues("distance_input");
		List<String> distanceValues = null;

		if (distanceList != null) {
			distanceValues = Arrays.asList(distanceList);
		}

		System.out.println("distanceValues" + distanceValues);

		List<Play_InfoDTO> list = this.dao.getDistanceSearchList(distanceValues);

		model.addAttribute("PlaySearchList", list);

		return "play/play_result";

	}

	@RequestMapping(value = "/play_craw.do")
	@ResponseBody
	public void craw_select(HttpServletRequest req, HttpServletResponse rep, String cont_tit) throws Exception {

		Gson gson = new Gson();

		boolean result = false;
		String url = "https://search.naver.com/search.naver?where=view&sm=tab_jum&query=" + cont_tit;

		Document doc = Jsoup.connect(url).get();
		Elements ele = doc.select("#main_pack");

		int divsize = ele.size();

		List<String> tit = new ArrayList<String>();
		List<String> cont = new ArrayList<String>();
		List<String> hrefList = new ArrayList<String>();
		List<String> imageUrlList = new ArrayList<String>();
		List<String> modifiedImageUrlList = new ArrayList<String>();

		Elements titElements = ele.select(".total_tit");
		Elements contElements = ele.select(".dsc_txt");
		Elements imageElements = ele.select(".thumb_fix");

		int limit = Math.min(3, titElements.size()); // 최대 3개 요소 선택
		List<Element> titElementsLimited = titElements.subList(0, limit);
		List<Element> contElementsLimited = contElements.subList(0, limit);
		List<Element> imageElementsLimited = imageElements.subList(0, limit);

		for (int i = 0; i < limit; i++) { // limit 갯수만큼 실행
			Element titElement = titElementsLimited.get(i);
			Element contElement = contElementsLimited.get(i);
			Element imageElement = imageElementsLimited.get(i);

			tit.add(titElement.text());
			String href = titElement.attr("href");

			hrefList.add(href);

			cont.add(contElement.text()); // 내용

			Elements imgElements = imageElement.select("img");
			if (!imgElements.isEmpty()) {
				Element imgElement = imgElements.first();

				String imageUrl = imgElement.attr("src");

				imageUrlList.add(imageUrl);

				int index = imageUrl.lastIndexOf('.'); // 마지막 점의 위치!

				if (index != -1) {
					String modifiedImageUrl = imageUrl.substring(0, index);

					modifiedImageUrlList.add(modifiedImageUrl);
				}
			}
		}

		Map<String, Object> resultMap = new HashMap<String, Object>();

		resultMap.put("Tit", tit);
		resultMap.put("ContCont", cont);
		resultMap.put("Href", hrefList);
		resultMap.put("ModifiedImageUrl", modifiedImageUrlList);

		result = true;

		rep.getWriter().print(gson.toJson(resultMap));

	}

	@RequestMapping(value = "/play_locationcheck.do", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public void loccheck(HttpServletRequest req, HttpServletResponse rep, @RequestParam("values") String valuesJson,
			Model model) throws IOException {
		Gson gson = new Gson();
		List<String> values = gson.fromJson(valuesJson, new TypeToken<List<String>>() {
		}.getType());
		// values 리스트를 사용하여 필요한 작업을 수행합니다.
		// 예: 리스트에 포함된 각 값에 대한 처리 로직 수행, 데이터베이스에 저장 등
		System.out.println("gson " + values);
		List<Play_InfoDTO> playList;

//		if (values == null) {
//			playList = daoIm.getPlayList();
//		} else 
		playList = daoIm.PlayList_loc(values);

		System.out.println("playlist " + playList);

		// ObjectMapper objectMapper = new ObjectMapper();
		// String responseJson = objectMapper.writeValueAsString(playList);

		Map<String, Object> resultSearch = new HashMap<String, Object>();
		resultSearch.put("SearchResult", playList);

		rep.getWriter().print(gson.toJson(resultSearch));
		// resultSearch 맵에는 "SearchResult"라는 키로 조회된 재생 목록인 playList를.
		// 이렇게 구성된 맵은 Gson 라이브러리의 toJson() 메서드를 사용하여 JSON 형식으로 변환.
		// 이후에 rep.getWriter().print(gson.toJson(resultSearch))를 통해 JSON 응답을 전송.
	}

	/* 조회수 +1 */
	@RequestMapping(value = "/playview_count.do", method = RequestMethod.POST)
	@ResponseBody
	public int PlayViewCount(@RequestParam("playIndex") int playIndex) {
		// 조회수를 증가시켜주는 메서드
		this.dao.readCount(playIndex);

		// 게시글의 상세 내역을 조회하는 메서드
		Play_InfoDTO dto = this.dao.playCont(playIndex);

		// 조회수를 증가한 게시물의 조회수를 반환
		int updatedPlayView = dto.getPlay_view();

		System.out.println("playIndex1111>>>" + playIndex);

		return updatedPlayView;

	}

	// play random
	@RequestMapping("play_random.do")
	public String random_list(HttpServletRequest request, Model model) {

		List<Play_InfoDTO> list = this.dao.getRandomList();

		model.addAttribute("RandomList", list);

		return "play/play_random";

	}

	@RequestMapping("play_out_random.do")
	public String random_out_list(HttpServletRequest request, Model model) {

		List<Play_InfoDTO> list = this.dao.getRandomOutList();

		model.addAttribute("RandomOutList", list);

		return "play/play_random_out";

	}

	@RequestMapping("play_in_random.do")
	public String random_in_list(HttpServletRequest request, Model model) {

		List<Play_InfoDTO> list = this.dao.getRandomInList();

		model.addAttribute("RandomInList", list);

		return "play/play_random_in";
	}

	@RequestMapping(value = "/play_likecheck.go", method = RequestMethod.POST)
	@ResponseBody
	public String play_likecheck(Play_LikeDTO dto) {
		int check = likedao.play_check(dto);

		if (check == 0) {
			return "unliked";
		} else {
			return "liked";
		}

	}

	// 좋아요
	// 리턴할꼐 2개 이면 json형식으로 한다.
	@RequestMapping(value = "/play_heart.go", method = RequestMethod.POST)
	@ResponseBody
	public String play_like(Play_LikeDTO dto, int check, HttpSession session) {

		System.out.println(check);

		String userId = (String) session.getAttribute("UserId");

		dto.setMember_id(userId);

		if (check == 0) {
			// insert
			int play_insert = likedao.play_likeinsert(dto);
			// 좋아요 총수 +1
			dao.play_likecountupdate(dto.getPlay_it());
			// System.out.println("선공");

			// json 객체 생성
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode responseJson = mapper.createObjectNode();
			responseJson.put("status", "liked");
			responseJson.put("likeCount", dao.getPlayLikeCount(dto.getPlay_it()));

			// json 문자열 반환
			return responseJson.toString();

		} else {
			System.out.println("후공");
			// delete
			likedao.play_likedelete(dto);
			// 좋아요 총수 -1
			dao.play_likecountmin(dto.getPlay_it());
			// 시퀀스 작업
			likedao.play_sequpdate(dto);

			// json 객체 생성
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode responseJson = mapper.createObjectNode();
			responseJson.put("status", "unliked");
			responseJson.put("likeCount", dao.getPlayLikeCount(dto.getPlay_it()));

			return responseJson.toString();
		}

	}

	// 검색
	@RequestMapping("list_search.do")
	public String search(Model model, @RequestParam("keyword") String keyword, HttpServletRequest request) {

		int totalRecord = this.dao.searchPlayCount(keyword);

		System.out.println("totalRecord >>>" + totalRecord);

		// 검색리스트
		List<Play_InfoDTO> searchList = this.dao.searchPlayList(keyword);

		System.out.println("searchList >>>>" + searchList);

		model.addAttribute("SearchList", searchList).addAttribute("TotalRecord", totalRecord);

		return "play/play_search_list";

	}

	// 댓글 작성
	@RequestMapping(value = "/play_comment_insert.do", method = RequestMethod.POST)
	@ResponseBody
	public String playreply_insert(@RequestParam("star") int star, @RequestParam("reply") String reply,
			@RequestParam("nickname") String nickname, @RequestParam("id") String id,
			@RequestParam("play_index") int play_index) {

		Play_CommentDTO commentdto = new Play_CommentDTO();
		commentdto.setPlay_index(play_index);
		System.out.println("play_index>>" + play_index);
		commentdto.setMember_nickname(nickname);
		System.out.println(nickname);
		commentdto.setMember_id(id);
		System.out.println(id);
		commentdto.setPlay_comment(reply);
		System.out.println("reply>>" + reply);
		commentdto.setPlay_star(star);
		System.out.println("star>>" + star);
		commentdto.setPlay_recommend(0);

		int comment_insert = comdao.commentInsert(commentdto);
		int updatePlayInfo = dao.updatePlayInfo(star, play_index);
		int startotal = dao.updatePlaycount(play_index);

		System.out.println("스타토탈" + startotal);
		// 댓글 추가
		if (comment_insert > 0 && updatePlayInfo > 0) {
			return "success";
		} else {
			return "fail";
		}
	}

	// 댓글 수
	@RequestMapping(value = "/comment_count.do", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public void reply_count(@RequestParam("playIndex") int playIndex, HttpServletResponse response) throws IOException {
		System.out.println(playIndex);
		int count = comdao.commentCount(playIndex);
		System.out.println("coiunt" + count);

		PrintWriter out = response.getWriter();
		out.println(count);
	}

	// 댓글 수정
	@RequestMapping(value = "/comment_modifyok.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String reply_modify(@RequestParam("commentNum") int commentindex,
			@RequestParam("modifyReply") String comment, @RequestParam("modifyNickname") String nickname) {

		Play_CommentDTO commentdto = new Play_CommentDTO();
		commentdto.setMember_nickname(nickname);
		System.out.println("nickname>>" + nickname);
		commentdto.setPlay_comment(comment);
		System.out.println("comment>>" + comment);
		commentdto.setPlay_comment_num(commentindex);
		System.out.println("commentindex>>" + commentindex);
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDate = LocalDate.now().format(dateFormatter);
		commentdto.setPlay_update(formattedDate);
		System.out.println("sysdate>>" + formattedDate);

		int modify = comdao.commentModify(commentdto);

		Map<String, Object> response = new HashMap<String, Object>();
		if (modify > 0) {
			response.put("success", true);
			response.put("comment", comment);
			response.put("nickname", nickname);
			response.put("update", formattedDate);
		} else {
			response.put("success", false);
		}

		Gson gson = new Gson();
		String jsonResponse = gson.toJson(response);

		return jsonResponse;

	}

	// 댓글 삭제
	@RequestMapping(value = "/comment_delete.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String reply_delete(@RequestParam("commentNum") int index) {
		Map<String, Object> response = new HashMap<String, Object>();
		int starnum = comdao.commentstarnum(index);
		int indexnum = comdao.commentindexnum(index);
		// 댓글 삭제
		int deleteResult = comdao.commetDelete(index);
		int deletePlayInfo = dao.deletePlayInfo(starnum, indexnum);
		int startotal = dao.updatePlaycount(indexnum);

		if (deleteResult > 0) {
			// 시퀀스 업데이트
			int seqResult = comdao.updateSeq(index);

			if (seqResult > 0) {
				response.put("success", true);
				response.put("message", "댓글이 삭제되었습니다.");
			} else {
				response.put("success", false);
				response.put("message", "시퀀스 업데이트에 실패하였습니다.");
			}
		} else {
			response.put("success", false);
			response.put("message", "댓글 삭제에 실패하였습니다.");
		}

		Gson gson = new Gson();
		String jsonResponse = gson.toJson(response);

		return jsonResponse;
	}

}
