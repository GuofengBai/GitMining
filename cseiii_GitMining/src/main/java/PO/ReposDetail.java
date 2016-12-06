package PO;

import java.io.Serializable;

/**
 * Created by guofengbai on 16-4-6.
 */
public class ReposDetail implements Serializable{

    public String names;
    public int commits;
    public int issues;
    public int pull_requests;

    public ReposDetail(){
        super();
    }


}
