import java.util.*;

public class VelocityCalculatorOfPorabola {
	
	public static void ruleExplain() {
		System.out.println("This is the Velocity Calculator of Shooter.");
		System.out.println("It'll calculate the initial velocity based on the angle of the shooter,"
				+ "\nAnd the position of the target given by the user.");
		System.out.println();
		
		System.out.println("The angle is in unit of degrees, and the input must be between 0 to 90.");
		System.out.println("The position input would be positive x and y, in SI units meters.");
		System.out.println();
		System.out.println("=======================================================================");
		System.out.println("=======================================================================");
		System.out.println();
	}
	
	
	public static void main(String[] args) {
		ruleExplain();
		
		Scanner s = new Scanner(System.in);
		
		boolean keepGoing = true;
		
			//loop that'll keep going until user said stop
		while (keepGoing) {
			System.out.println();
			
			//get access to the input
			System.out.print("Your shooter's angle -> ");
			double angle = s.nextDouble();
			System.out.println();
			
			System.out.print("Your target's x-position -> ");
			double x = s.nextDouble();
			System.out.println();
			
			System.out.print("Your target's y-position -> ");
			double y = s.nextDouble();
			System.out.println();
			System.out.println("=======================================================================");
			System.out.println();
			
			
			//get the object
			VelocityInPorabola v = new VelocityInPorabola(angle, x, y);
			
			//output
			System.out.println();
			System.out.println(v.result());
			if (v.isReaching()) {
				System.out.println(v.conditionWhenReach());
			}
			System.out.println();
			
			
			//ask if user want to keep using this program
			System.out.print("Keep using this calculateror? (yes/no) -> ");
			String result = s.next().toLowerCase();
			
			if (result.equals("no")) {
				keepGoing = false;
			}
		}
		
		System.out.println("Thank you for using this program.");
		
		
		//close the scanner
		s.close();
	}
}
