package VO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Statistic {
	
	public double sum;
	public double arithmetic_mean;
	public double geometrical_mean;
	public double harmonic_mean;
	public double median;
	public double mode;
	public double variance;
	public int num;
	
	public Statistic(Collection<Double> set){
		super();
		sum=0;
		num=set.size();
		double product = 0;
        double sum1 = 0;
        int num1=0;
		for(Double d:set){
			sum+=d;
			if (d > 0) { // 去掉0
                product += Math.log(d);
                sum1 += 1/d;
                num1++;
            }
		}
		arithmetic_mean=sum/num;
		product/= num1;
        geometrical_mean = Math.pow(Math.E, product);
        harmonic_mean = num1/sum1;
        List<Double> list=new ArrayList();
        list.addAll(set);
        median = list.get((list.size() + 1) / 2);
        Map<Double,Integer> map=new HashMap();
        for(Double d:set){
        	if(map.containsKey(d)){
        		map.replace(d, map.get(d)+1);
        	}else{
        		map.put(d, 1);
        	}
        }
        int num2=0;
        double value = 0;
        for(Double d:map.keySet()){
        	if(num2<map.get(d)){
        		num2=map.get(d);
        		value=d;
        	}
        }
        mode=value;
        
        double sum2=0;
        for(Double d:set){
        	sum2+=(d-arithmetic_mean)*(d-arithmetic_mean);
        }
        variance=sum2/num;

		
	}

}
