package model;
import java.awt.*;
import java.awt.event.*;
/**
 * Klasse für Klammernprüfung in einem String.
 * @author smodlich
 * inputString ist der Eingabe String für den die Klammernprüfung durchgeführt werden soll
 */
public class ParenMatcher{
  private String inputString;
  	
  private boolean match(char c, char d){
    switch(c){
      case '(': return(d==')');
      case '[': return(d==']');
      case '{': return(d=='}');
      default : return false;
    }
  }
  	/**
  	 * führt die Klammernprüfung im inputString durch
  	 * @return
  	 */
  public boolean parenMatch(){
    
    int n=inputString.length();
    Stack parenStack=new Stack(n);
    int i=0;
    char c,d;
    while(i<n){
      d=inputString.charAt(i);
      if(d=='('||d=='['||d=='{'){          
      
    	  parenStack.push(d);

      }else if(d==')'||d==']'||d=='}'){
        if(parenStack.empty()){
        	return false;

        }else{
          c=((Character)parenStack.pop()).charValue();
          if(!match(c,d)){
        	  return false;

          }
        }
      }
      ++i;
    }
    if(parenStack.empty()){
    	
    		return true;

    }else{
   
    	while(!parenStack.empty()&&(i<n))
    	{
    		
    		c=((Character)parenStack.pop()).charValue();
    		d=inputString.charAt(i);
    		if(c==d)
    			;
    		else
    			{
    			return false;
    					}
    		
    	}
   	  return false;

    }
  }

  	
  public void setInput(String input){
    inputString=input;
  }
}
