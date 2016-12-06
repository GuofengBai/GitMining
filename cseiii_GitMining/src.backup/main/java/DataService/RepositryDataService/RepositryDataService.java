package DataService.RepositryDataService;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import PO.ReposPO;
import VO.RepoListLineItem;

import org.json.*;

import VO.RepoSortType;

public interface RepositryDataService {
	
	public Vector<RepoListLineItem> getList(int page);

	public void search(String key);

	public void setOrder(RepoSortType type);
	
	public ReposPO getRepositry(String Name);
	
	public List<String> getContributors(String Name);

	public List<String> getCollaborators(String Name);
	
	public Map<String,Integer> getReposRelatedByContributor(String Name);
	
	public List<String> getKeywords(String full_name);
	
	public List<String> getReposWithOneKeyword(String keyword);
	
	public List<String> getReposWithSomeKeywords(List<String> keywords);
	
	public int getTotalPage();

}
