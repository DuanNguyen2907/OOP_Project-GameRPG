package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Utility {
	
	private static int startTime;  
    private static int endTime;  
    private static int frameTimes = 0;  
    private static short frames = 0; 
	
	public BufferedImage scaleImage(BufferedImage original,int width,int height) {
		BufferedImage scaledImage = new BufferedImage(width,height,original.getType());
		Graphics2D g2 = scaledImage.createGraphics();
		g2.drawImage(original, 0, 0, width, height, null);
		g2.dispose();
		
		return scaledImage;
	}
	public final static void StartCounter()  
    {  
        //get the current time  
        startTime = (int) System.currentTimeMillis();  
    } 
	public final static void StopAndPost()  
    {  
        //get the current time  
        endTime = (int) System.currentTimeMillis();  
        //the difference between start and end times  
        frameTimes = frameTimes + endTime - startTime;  
        //count one frame  
        ++frames;  
        //if the difference is greater than 1 second (or 1000ms) post the results  
        if(frameTimes >= 1000)  
        {  
            //post results at the console  
            System.out.println("FPS"+Long.toString(frames));  
            //reset time differences and number of counted frames  
            frames = 0;  
            frameTimes = 0;  
        }  
    } 
}
