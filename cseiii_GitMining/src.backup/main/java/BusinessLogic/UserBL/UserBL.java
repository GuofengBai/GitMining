package BusinessLogic.UserBL;

import java.util.Map;
import java.util.Vector;

import DataService.RepositryDataService.RepositryDataService;

import org.json.JSONObject;

import cseiii_GitMining.DataFactory;
import PO.UserPO;
import VO.UserListLineItem;
import VO.UserSortType;
import VO.UserVO;
import BusinessLogicService.UserBLService.UserBLService;
import DataService.UserDataService.UserDataService;

public class UserBL implements UserBLService {
	
	public static UserSortType sortType;
	
	public static int page;

	public static String key;
	
	public static int total_page;
	
	public UserBL(){
		
		init();
		
	}
	
	public void init(){

		setSortType(UserSortType.FOLLOWERS);
		
	}
	
	public int getPage(){
		return page;
	}
	
	public int getTotalPage(){
		return DataFactory.getUserData().getTotalPage();
	}

	public Vector<UserListLineItem> getUserList(int page) {
		
		UserDataService user=DataFactory.getUserData();

		Vector<UserListLineItem> ul = user.getList(page);
		
		return ul;
	}

	public Vector<UserListLineItem> getNextPage() {

		if(page!=total_page){
			page++;
		}
		
		return getUserList(page);
	}

	public void setPage(int page) {

		this.page=page;
		
	}

	public void setKey(String key) {

		this.key=key;

		UserDataService ud=DataFactory.getUserData();

		ud.search(key);

		setPage(0);
		
		total_page=getTotalPage();
		
	}
	
	public void setSortType(UserSortType type){
		
		this.sortType=type;

		setPage(0);
		
		UserDataService user=DataFactory.getUserData();
		
		user.setOrder(type);
		
		key="";
		
		total_page=getTotalPage();
		
	}

	public UserVO getUser(String Name) {

		UserDataService userData=DataFactory.getUserData();
		
		UserPO po=userData.getUser(Name);

		UserVO vo=new UserVO(po);
		
		return vo;
		
	}

	public Vector<UserListLineItem> getPrePage() {

		if(page>1){
			
			page--;
			
		}
		
		return getUserList(page);
	}

	public Object[][] getCoworkers(String Name) {

		UserDataService data=DataFactory.getUserData();
		
		Map<String,Integer> map=data.getCoworkers(Name);
		
		Object[][] result=new Object[map.size()][2];
		
		int i=0;
		
		for(String s:map.keySet()){
			result[i][0]=s;
			result[i][1]=map.get(s);
			i++;
		}
		
		return result;
	}



}
