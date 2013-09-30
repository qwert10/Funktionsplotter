import java.awt.*;
import java.awt.event.*;

public class ParenMatcher{
  private String inputString, outputString;
  	
  private boolean match(char c, char d){
    switch(c){
      case '(': return(d==')');
      case '[': return(d==']');
      case '{': return(d=='}');
      default : return false;
    }
  }
  	
  public boolean parenMatch(){
    
    int n=inputString.length();
    Stack parenStack=new Stack(n);
    int i=0;
    char c,d;
    while(i<n){
      d=inputString.charAt(i);
      if(d=='('||d=='['||d=='{'){          //šffnende Klammer
      
    	  parenStack.push(d);

      }else if(d==')'||d==']'||d=='}'){
        if(parenStack.empty()){
        	return false;
            //Stack leer

        }else{
          c=((Character)parenStack.pop()).charValue();
          if(!match(c,d)){
            //keine Ÿbereinstimmung von d und dem top vom Stack
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

  public String getOutput(){
    return outputString;
  }
}
