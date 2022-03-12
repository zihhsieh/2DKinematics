/**
 * 
 * @author hsieh
 *
 *This Program will calculate the velocity need for shooting a target in (x, y) position
 *when the shooter is on (0, 0) position, with angle theta above horizontal.
 */

public class VelocityInPorabola {
	//requirements of calculating velocity
	private double theta; //measured in radians,  0 < theta < pi/2
		//position of target
	private double xPos; //measured in SI unit m
	private double yPos;
	
	//variables related to porabola
	private boolean canReach;
	private double time;
	private double v0;
	private boolean isGoingUp;
	
	//constructor
	public VelocityInPorabola(double angle, double x, double y) {
		//initialize the speed
		theta = angle * Math.PI / 180; //convert to radian
		xPos = x;
		yPos = y;
		
		//first determine if calculation is possible
		canReach = canReach();
		
		//calculate actual initial velocity
		if (canReach && xPos >0) {
			v0 = velocityCalculation();
			time = xPos / (v0 * Math.cos(theta)); //the time it spent to actually reach the target
			isGoingUp = isGoingUp();
			
		} else if (theta == Math.PI /2 && x == 0) {
			v0 = specialVelocityCalculation();
			time = v0 / 9.8;
			isGoingUp = isGoingUp();
		}
	}
	
	//the public methods
	public String result() {
		if (canReach) {
			//round the velocity to thousandth
			double result = v0 * 1000.0;
			result = Math.round(result);
			result /= 1000.0;
			
			return "The Initial Velocity Required: " + result + "m/s.";
		}
		
		//result when it can't reach.
		return "Sorry, but this angle doesn't have any velocity that can reach the target.";
	}
	
	public String conditionWhenReach() {
		if (isGoingUp) {
			return "When the ball reaches the target, it is still going upward; \nIt's better to increase the angle.";
		}
		
		return ""; //
	}
	
		//accessor of canReach
	public boolean isReaching() {
		return canReach;
	}
	
		//accessor of isGoingUp
	public boolean goingUp() {
		return isGoingUp;
	}
	
	
	//internal implementations
	/**
	 * @return -> if the angle can reach the target or not
	 */
 	private boolean canReach() {
		//discriminant: tan(theta) * x - y >0
		if (theta < Math.PI /2) {
			double discriminant = Math.tan(theta) * xPos - yPos;
			return discriminant >0;
			
		} else if (theta == Math.PI /2 && xPos == 0) {
			return true;
		}
		
		return false; //when theta / pi/2, then tan(theta) is undefined
	}
	
	/**
	 * @return -> initial velocity when x >0
	 */
	private double velocityCalculation() {
		//equation of velocity (when theta < pi/2)
		double numerator = 9.8 * xPos * xPos;
		double denominator = 2.0 * Math.pow(Math.cos(theta), 2) * (Math.tan(theta) * xPos - yPos);
		double vSquare = numerator / denominator;
		double v = Math.sqrt(vSquare);
		
		return v;
	}
	
	/**
	 * @return -> iniial velocity (only when theta = pi/2 & x = 0) -> velocity for vertical shooting
	 */
	private double specialVelocityCalculation() {
		//v0 = sqrt(2gh)
		double v0 = Math.sqrt(2.0 * 9.8 * yPos);
		
		return v0;
	}
	
	/**
	 * @return -> when it reaches he target, is it still flying upward or not
	 */
	private boolean isGoingUp() {
		//calculate the final velocity when it reached the target
		double vF = v0 * Math.sin(theta) - 9.8 * time;
		
		//if vF >0, then it's still going up
		return vF >0;
	}
}
