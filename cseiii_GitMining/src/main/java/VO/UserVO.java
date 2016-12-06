package VO;

import java.util.List;

import org.json.JSONObject;

import PO.UserPO;

public class UserVO {
	
	public String id;
	public String type;
	public String name;
	public String login;

	public String company;
	public String location;
	public String email;
	public String bio;
	public String blog;
	

	public List<String> ownsRepos;
	public List<String> relatedRepos;
	public int followers;
	public int following;

	public String created_at;
	public String updated_at;
	
	public UserVO(){
		super();
	}
	
	public UserVO(UserPO po){
		super();
		login=po.login;
		id=po.id;
		type=po.type;
		name=po.name;
		email=po.email;
		company=po.company;
		location=po.company;
		bio=po.bio;
		blog=po.blog;
		ownsRepos=po.ownsRepos;
		relatedRepos=po.relatedRepos;
		followers=po.followers;
		following=po.following;
		created_at=po.created_at;
		updated_at=po.updated_at;
	}

}