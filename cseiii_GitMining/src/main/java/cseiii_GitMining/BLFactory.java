package cseiii_GitMining;

import BusinessLogic.RepositryBL.RepositryBL;
import BusinessLogic.StatisticBL.StatisticBL;
import BusinessLogic.UserBL.UserBL;
import BusinessLogicService.RepositryBLService.RepositryBLService;
import BusinessLogicService.StatisticBLService.StatisticBLService;
import BusinessLogicService.UserBLService.UserBLService;

public class BLFactory {
	
	public static RepositryBLService repo;
	
	public static UserBLService user;
	
	public static StatisticBLService statistic;
	
	public BLFactory(){
		
		init();
		
	}

	public static void init() {
		
		repo=null;
		
		user=null;
		
		statistic=null;
		
	}
	
	public static RepositryBLService getRepositryBL(){
		
		if(repo==null){
			
			repo=new RepositryBL();
			
		}
		
		return repo;
	}
	
	public static UserBLService getUserBL(){
		
		if(user==null){
			
			user=new UserBL();
			
		}
		
		return user;
	}
	
	public static StatisticBLService getStatisticBL(){
		
		if(statistic==null){
			
			statistic=new StatisticBL();
			
		}
		
		return statistic;
	}

}
