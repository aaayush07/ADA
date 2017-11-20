package ada;
import java.io.*;
import java.util.*;
import java.lang.*;
public class Logic {
	double computeResult(String saveFile1,String saveFile2) {
		//fill the TreeSet
		//Tresset declaration
		TreeMap<String,Integer> obj = new TreeMap<String,Integer>();
		TreeMap<String,Integer> obj1 = new TreeMap<String,Integer>();

		//reading file1
		String File1 = new String("");
		try(FileReader fr=new FileReader(saveFile1);    
		        BufferedReader bf =new BufferedReader(fr);   ){
		while((File1= bf.readLine())!=null){
			String []arr = File1.split(" ");
			for(String S1 : arr){
				S1.trim();S1.toLowerCase();
			    boolean testVar = obj.containsKey(S1);
			    if(testVar == true){
			    	int oldValue = obj.get(S1);
			    	obj.replace(S1, oldValue, oldValue+1);
			    }
			    else{
			    	obj.put(S1,1);
			    }
			}
		}
		}
		catch(Exception e){
			System.out.print(e);
		}

		//reading for file2
		        String File2 = new String("");
		        try(FileReader fr=new FileReader(saveFile2);    
		                BufferedReader bf1 =new BufferedReader(fr);   ){
		while((File2 = bf1.readLine())!=null){
			String []arr1 = File2.split(" ");
			for(String S2 : arr1){
				S2.trim();S2.toLowerCase();
			boolean testVar2 = obj1.containsKey(S2);
			if(testVar2 == true){
			int oldValue = obj1.get(S2);
		   	obj1.replace(S2, oldValue, oldValue+1);
		}
		    else{
		  	obj1.put(S2,1);	
		  	}	
			}
		}
		}
		catch(Exception e){
			System.out.print(e);
		}
	

    long sum=0,sum1=0,sum2=0,sum3=0,sum4=0,sum5=0,mul=0,mul1=0,flag=0;
Set c = obj.entrySet();
Iterator itr = c.iterator();
Set c1 = obj1.entrySet();
Iterator itr1 = c1.iterator();

while(itr.hasNext()){
Map.Entry me = (Map.Entry)itr.next();
String xyz = (String)me.getKey();
if(obj1.containsKey(xyz)){
int val = (int)obj1.get(xyz);
sum = (int)me.getValue();
sum4 = sum*sum;
sum1 += sum4;
sum2 = val;
sum5 = sum2*sum2;
sum3 += sum5;
mul = sum*sum2;
mul1 += mul;
}
}
double result1=0,result2=0,result3=0,answer=0,result4=0;
result1 = Math.sqrt(sum1);
result1 = Math.round(result1*10000000.0)/10000000.0;
result2 = Math.sqrt(sum3);
result2 = Math.round(result2*10000000.0)/10000000.0;
result3 = result1*result2;
result4 = mul1/result3;
result4 = Math.round(result4*100.0)/100.0;
answer = Math.acos(result4);
answer = (answer/Math.acos(0))*100;
return answer;
}
	
	void filedelete(String saveFile1) {
		if(saveFile1!=null) {
			File file = new File(saveFile1);
			file.delete();
		}
	}
}
