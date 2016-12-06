package VO;

import java.util.List;

import PO.ReposPO;

public class RepositryVO {
	public String reponame;
	public String ownername;
	public String description;
	public String html_url;
	public int stars;
	public int forks;
	public int watchers;//浏览�?
	public int subscribers;//订阅�?
	public int commits;
	public int issues;
	public int pull_requests;
	public List contributors;
	public List collaborators;
	public String language;
	public List<String> keyword;
	
	public RepositryVO(){
		super();
	}
	
	public RepositryVO(ReposPO po){
		super();
		description=po.description;
		forks=po.forks;
		html_url=po.html_url;
		language=po.language;
		ownername=po.ownername;
		reponame=po.reponame;
		stars=po.stars;
		subscribers=po.subscribers;
		watchers=po.watchers;
		contributors=po.contributors;
		collaborators=po.collaborators;
		commits=po.commits;
		issues=po.issues;
		pull_requests=po.pull_requests;
		keyword=po.keyword;
	}
	
}
