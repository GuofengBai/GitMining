package PO;

import java.io.Serializable;
import java.util.List;

public class UserPO implements Serializable{
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
	public UserPO(){
		super();
	}
}
