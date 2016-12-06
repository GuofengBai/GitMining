package BusinessLogic.RepositryBL;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONObject;

import cseiii_GitMining.DataFactory;
import PO.ReposPO;
import VO.RepoListLineItem;
import VO.RepoSortType;
import VO.RepositryVO;
import BusinessLogicService.RepositryBLService.RepositryBLService;
import DataService.RepositryDataService.RepositryDataService;

public class RepositryBL implements RepositryBLService {

	public static RepoSortType sortType;
	
	public static String key;
	
	public static int page;
	
	public static int total_page;
	
	public RepositryBL(){
		
		init();
		
	}
	
	public void init(){
		
		sortType=RepoSortType.STARS;
		
		key="";
		
		page=0;
		
		total_page=getTotalPage();
		
	}

	public Vector<RepoListLineItem> getRepositryList(int page) {
		// TODO Auto-generated method stub
		RepositryDataService repo=DataFactory.getRepositryData();
		
		return repo.getList(page);

	}

	public RepositryVO getRepositry(String Name) {

		RepositryDataService repoData=DataFactory.getRepositryData();
		
		ReposPO po=repoData.getRepositry(Name);
		
		RepositryVO vo=new RepositryVO(po);
		
		return vo;
	
	}

	public boolean setPage(int page) {
		if(page<=total_page){
			this.page=page;
			return true;
		}
		return false;
	
	}
	
	public int getPage(){
		return page;
	}
	
	public int getTotalPage(){
		return DataFactory.getRepositryData().getTotalPage();
	}

	public void setSortType(RepoSortType type) {

		this.sortType=type;

		setPage(0);

		RepositryDataService repo = DataFactory.getRepositryData();

		repo.setOrder(type);

		key="";
		
		total_page=getTotalPage();
		
	}

	public void setKey(String key) {
		
		RepositryDataService repoData=DataFactory.getRepositryData();
		
		repoData.search(key);

		this.key=key;

		setPage(0);
		
		total_page=getTotalPage();
		
	}

	public Vector<RepoListLineItem> getNextPage() {
		if(page!=total_page){
			page++;
		}
		
		return getRepositryList(page);
		
	}

	public Vector<RepoListLineItem> getPrePage() {
		
		if(page>1){
			
			page--;
			
		}
		
		return getRepositryList(page);
	}

	public Object[][] getReposRelatedByContributor(String Name) {
		
		RepositryDataService data=DataFactory.getRepositryData();
		
		Map<String,Integer> map=data.getReposRelatedByContributor(Name);
		
		Object[][] result=new Object[map.size()][2];
		
		int i=0;
		
		for(String s:map.keySet()){
			result[i][0]=s;
			result[i][1]=map.get(s);
			i++;
		}
		
		return result;
	}

	public List<String> getKeywords(String full_name) {
		// TODO Auto-generated method stub
		return DataFactory.getRepositryData().getKeywords(full_name);
	}

	public List<String> getReposWithOneKeyword(String keyword) {
		// TODO Auto-generated method stub
		return DataFactory.getRepositryData().getReposWithOneKeyword(keyword);
	}

	public List<String> getReposWithSomeKeywords(List<String> keywords) {
		// TODO Auto-generated method stub
		return DataFactory.getRepositryData().getReposWithSomeKeywords(keywords);
	}

}
