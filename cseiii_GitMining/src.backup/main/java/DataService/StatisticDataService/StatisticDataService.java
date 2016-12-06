package DataService.StatisticDataService;

public interface StatisticDataService {
	
	public Object[][] getCommitsStateOfContributors(String full_name);
	
	public Object[] getCommitsStateByRepos(String full_name);
	
	public Object[][] getCommitsMessageByRepos(String full_name);
	
	public Object[][] getCommitsStateOfRelatedRepos(String login);
	
	public Object[] getCommitsStateByUser(String login);
	
	public Object[][] getCommitsMessageByUser(String login);
	
	public Object[] getCommitsStatusOfUserTillOneRepository(String full_name,String login);

}
