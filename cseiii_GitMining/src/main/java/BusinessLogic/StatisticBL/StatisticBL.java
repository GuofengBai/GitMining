package BusinessLogic.StatisticBL;

import cseiii_GitMining.DataFactory;
import BusinessLogicService.StatisticBLService.StatisticBLService;

public class StatisticBL implements StatisticBLService {

	public Object[][] getCommitsStateOfContributors(String full_name) {
		// TODO Auto-generated method stub
		return DataFactory.getStatisticData().getCommitsStateOfContributors(full_name);
	}

	public Object[] getCommitsStateByRepos(String full_name) {
		// TODO Auto-generated method stub
		return DataFactory.getStatisticData().getCommitsStateByRepos(full_name);
	}

	public Object[][] getCommitsMessageByRepos(String full_name) {
		// TODO Auto-generated method stub
		return DataFactory.getStatisticData().getCommitsMessageByRepos(full_name);
	}

	public Object[][] getCommitsStateOfRelatedRepos(String login) {
		// TODO Auto-generated method stub
		return DataFactory.getStatisticData().getCommitsStateOfRelatedRepos(login);
	}

	public Object[] getCommitsStateByUser(String login) {
		// TODO Auto-generated method stub
		return DataFactory.getStatisticData().getCommitsStateByUser(login);
	}

	public Object[][] getCommitsMessageByUser(String login) {
		// TODO Auto-generated method stub
		return DataFactory.getStatisticData().getCommitsMessageByUser(login);
	}

	public Object[] getCommitsStatusOfUserTillOneRepository(String full_name,
			String login) {
		// TODO Auto-generated method stub
		return DataFactory.getStatisticData().getCommitsStatusOfUserTillOneRepository(full_name, login);
	}

}
