package PO;

import java.io.Serializable;
import java.util.List;

/**
 * Created by guofengbai on 16-4-6.
 */
public class ReposPO implements Serializable{

	public String reponame;
	public String ownername;
	public String description;
	public String html_url;
	public int stars;
	public int forks;
	public int watchers;//浏览者
	public int subscribers;//订阅者
	public int commits;
	public int issues;
	public int pull_requests;
	public List contributors;
	public List collaborators;
	public String language;
	public List<String> keyword;

    public ReposPO(){
        super();
    }

}
