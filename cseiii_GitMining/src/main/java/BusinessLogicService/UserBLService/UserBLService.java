package BusinessLogicService.UserBLService;

import java.util.Vector;

import VO.UserListLineItem;
import VO.UserSortType;
import VO.UserVO;

public interface UserBLService {
	
	public Vector<UserListLineItem> getUserList(int page);
	
	public Vector<UserListLineItem> getNextPage();
	
	public Vector<UserListLineItem> getPrePage();
	
	public void setPage(int page);
	
	public void setKey(String key);
	
	public void setSortType(UserSortType type);
	
	public UserVO getUser(String Name);
	
	public Object[][] getCoworkers(String Name);
	
	public int getPage();
	
	public int getTotalPage();

}
