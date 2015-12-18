package org.cafelabs.svm;
//last version
public class Memory {
	static Integer ram[] = new Integer[1024];
	//static String rom[] = new String[8];
	static {
		for(int i =0;i<ram.length;i++){
			ram[i]=0;
		}
	}
	
	/**
	 * Set value equals 'value' of RAM`s element on position = 'position' 
	 * 
	 * @param int position
	 * @param int value
	 */

	public static void setRam(int position, int value){
		ram[position] = new Integer(value);
	}
	
	/**
	 * Get value of RAM`s element on position = 'position'
	 */

	public static int getRam(int position){
		return ram[position];
	}
	
	/**
	 * Dump RAM
	 */
	public static void dump(){
		int k =0;
		//String formatted=Integer.toHexString(k)+":\t";
		String formatted=Integer.toString(k)+":\t";
		for (Integer i : ram) {
			k++;
			StringBuffer some =new StringBuffer("00000000");
			String format = Integer.toHexString(i).toUpperCase();
			//String format = Integer.toString(i).toUpperCase();
			for(int j = 0;j < format.length();j++){
				some.setCharAt(7-format.length()+ 1 + j, format.charAt(j));
			}
			formatted += "  " +some; 
			if(k%8 == 0 && (k != ram.length)) {
				//formatted+=String.format("\n"+Integer.toHexString(k)+":\t");
				formatted+=String.format("\n"+Integer.toString(k)+":\t");
			}
		}
		System.out.println(formatted);
	}
}


	