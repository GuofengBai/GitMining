package VO;

public enum UserSortType {
	
	PUBLIC_REPOS("public_repos"),PUBLIC_GISTS("public_gists"),FOLLOWERS("followers"),
	FOLLOWING("following"),CREATED_AT("created_at"),UPDATED_AT("updated_at");
	
	private String sortType;
	
	private UserSortType(String str){
		
		this.sortType=str;
		
	}
	
	public String toString(){
		return sortType;
	}

}
