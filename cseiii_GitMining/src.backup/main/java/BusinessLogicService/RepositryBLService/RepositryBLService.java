package BusinessLogicService.RepositryBLService;

import java.util.List;
import java.util.Vector;

import VO.RepoListLineItem;
import VO.RepoSortType;
import VO.RepositryVO;

public interface RepositryBLService {
	
	public Vector<RepoListLineItem> getRepositryList(int page);
	
	public Vector<RepoListLineItem> getNextPage();
	
	public Vector<RepoListLineItem> getPrePage();
	
	public boolean setPage(int page);
	
	public int getPage();
	
	public int getTotalPage();
	
	public void setSortType(RepoSortType type);
	
	public void setKey(String key);
	
	public RepositryVO getRepositry(String Name);
	
	public Object[][] getReposRelatedByContributor(String Name);
	
	public List<String> getKeywords(String full_name);
	
	public List<String> getReposWithOneKeyword(String keyword);
	
	public List<String> getReposWithSomeKeywords(List<String> keywords);

}
