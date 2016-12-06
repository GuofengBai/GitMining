package cseiii_GitMining;

import Data.RepositryData.RepositryData;
import Data.StatisticData.StatisticData;
import Data.UserData.UserData;
import DataService.RepositryDataService.RepositryDataService;
import DataService.StatisticDataService.StatisticDataService;
import DataService.UserDataService.UserDataService;

public class DataFactory {
	
	public static RepositryDataService repo;
	
	public static UserDataService user;
	
	public static StatisticDataService statistic;
	
	public DataFactory(){
		
		init();
		
	}

	public static void init() {
		
		repo=null;
		
		user=null;
		
		statistic=null;
		
	}
	
	public static RepositryDataService getRepositryData(){
		
		if(repo==null){
			
			repo=new RepositryData();
			
		}
		
		return repo;
	}
	
	public static UserDataService getUserData(){
		
		if(user==null){
			
			user=new UserData();
			
		}
		
		return user;
	}
	
	public static StatisticDataService getStatisticData(){
		
		if(statistic==null){
			
			statistic=new StatisticData();
			
		}
		
		return statistic;
	}

}
