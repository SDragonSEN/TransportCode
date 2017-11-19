package autoStudy;

import java.io.Serializable;
import java.util.Arrays;

public class HistoryData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9202223688823117637L;
	
	int[] before = new int[10];
	int beforeNum = 0;
	int[] success = new int[10];
	int successNum = 0;
	int[] late = new int[10];
	int lateNum = 0;
	public HistoryData(){
		for (int i = 0; i < 10; i++){
			before[i] = Integer.MAX_VALUE;
			success[i] = Integer.MAX_VALUE;
			late[i] = Integer.MAX_VALUE;
		}
	}
	public void addSuccess(int x){
		if (successNum < 10){
			success[successNum] = x;
			successNum++;
		}else{
			if (Math.abs(x - success[0]) > Math.abs(x - success[9])){
				success[0] = x;
			}else{
				success[9] = x;
			}
		}
		
		Arrays.sort(success);
	}
	public void addLate(int x){
		if (lateNum < 10){
			late[lateNum] = x;
			lateNum++;
		}else{
			if (Math.abs(x - late[0]) > Math.abs(x - late[9])){
				late[0] = x;
			}else{
				late[9] = x;
			}
		}
		
		Arrays.sort(late);
	}
	public void addBefore(int x){
		if (beforeNum < 10){
			before[beforeNum] = x;
			beforeNum++;
		}else{
			if (Math.abs(x - before[0]) > Math.abs(x - before[9])){
				before[0] = x;
			}else{
				before[9] = x;
			}
		}
		
		Arrays.sort(before);
	}

	public int getValue(){
		int sucBegin  = 0;
		int sucEnd    = successNum;
		int befBegin  = 0;
		int befEnd    = beforeNum;
		int lateBegin = 0;
		int lateEnd   = lateNum;
		
		while(true){
			if (befBegin >= befEnd || lateBegin >= lateEnd){
				break;
			}
			if (before[befEnd-1] < late[lateBegin]){
				break;
			}
			befEnd--;
			lateBegin++;
		}
		while(true){
			if (befBegin >= befEnd || sucBegin >= sucEnd){
				break;
			}
			if (before[befEnd-1] < success[sucBegin]){
				break;
			}
			befEnd--;
			sucBegin++;
		}
		while(true){
			if (sucBegin >= sucEnd || lateBegin >= lateEnd){
				break;
			}
			if (success[sucEnd-1] < late[lateBegin]){
				break;
			}
			sucEnd--;
			lateBegin++;
		}
		if (sucBegin < sucEnd){
			int value = 0;
			for (int i = sucBegin; i < sucEnd; i++){
				value += success[i];
			}
			return value / (sucEnd - sucBegin);
		}
		
		if (befBegin < befEnd && lateBegin < lateEnd){
			return (before[befEnd - 1] + late[lateBegin]) / 2;		
		}
		
		if (befBegin < befEnd){
			return before[befEnd - 1] + 300;
		}
		
		if (lateBegin < lateEnd){
			return late[lateBegin] - 300;
		}
		
		if (successNum != 0){
			int value = 0;
			for (int i = 0; i < successNum; i++){
				value += success[i];
			}
			return value / successNum;
		}
		
		return 0;
	}
	public void print(){
		System.out.print("抢早了的误差调节值:\t");
		for (int i = 0; i < beforeNum; i++){
			System.out.print(before[i]+"\t");
		}
		System.out.println();
		System.out.print("抢到了的误差调节值:\t");
		for (int i = 0; i < successNum; i++){
			System.out.print(success[i]+"\t");
		}
		System.out.println();
		System.out.print("抢晚了的误差调节值:\t");
		for (int i = 0; i < lateNum; i++){
			System.out.print(late[i]+"\t");
		}
		System.out.println();
	}
	
}
