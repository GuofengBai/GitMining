package DataService.UserDataService;

import PO.UserPO;
import VO.UserListLineItem;
import VO.UserSortType;

import java.util.List;
import java.util.Map;
import java.util.Vector;

public interface UserDataService {
	
	public Vector<UserListLineItem> getList(int page);
	
	public UserPO getUser(String Name);
	
	public List<String> getUserOwnsRepos(String Name);

	public List<String> getUserRelatedRepos(String Name);

	public void search(String key);
	
	public void setOrder(UserSortType type);

	public void setNormal();
	
	public Map<String,Integer> getCoworkers(String Name);
	
	public int getTotalPage();

}
