package VO;

public enum RepoSortType {
	
	FORKS("forks"),STARS("stars"),COMMITS("commits"),ISSUES("issues"),PULLS("pulls"),
	WATHCERS("watchers"),SUBSCRIBERS("subscribers"),SIZE("size"),CREATED_AT("created_at"),
	UPDATED_AT("updated_at"),PUSHED_AT("pushed_at");
	
	private String sortType;
	
	private RepoSortType(String str){
		
		this.sortType=str;
		
	}
	
	public String toString(){
		return sortType;
	}

}
