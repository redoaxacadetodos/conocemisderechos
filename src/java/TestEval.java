import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


public class TestEval {

	
	public static void main(String[] vasc){
		
		
		
		
	      	ScriptEngineManager script = new ScriptEngineManager();
	       
	        ScriptEngine js = script.getEngineByName("JavaScript");
	        try {
	        	
	        	
	        	
			System.out.println(js.eval("eval('[(5/10-1) -1] *100')"));
			
	        
	        
	        } catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 
	}
	
	
}
