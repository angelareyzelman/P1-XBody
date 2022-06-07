

/**
 * Celestial Body class for NBody
 * Modified from original Planet class
 * used at Princeton and Berkeley
 * @author ola
 *
 * If you add code here, add yourself as @author below
 * @author angelareyzelman
 *
 */
public class CelestialBody {

	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName;


	public CelestialBody(double xp, double yp, double xv,
						 double yv, double mass, String filename){
		myXPos = xp;
		myYPos = yp;
		myXVel = xv;
		myYVel = yv;
		myMass = mass;
		myFileName = filename;

	}

	public double getX() {return myXPos;}

	public double getY() {return myYPos;}

	public double getXVel() {return myXVel;}

	public double getYVel() {return myYVel;}

	public double getMass() {return myMass;}

	public String getName() {return myFileName;}


	public double calcDistance(CelestialBody b) {
		double dx = 0.0;
		double dy = 0.0;
		double rDist = 0.0;
		dx = (myXPos-b.myXPos);
		dy = (myYPos-b.myYPos);
		rDist = Math.sqrt((dx*dx)+(dy*dy));

		return rDist;
	}

	public double calcForceExertedBy(CelestialBody b) {
		double force = 0.0;
		double G = 6.67*1e-11;
		double r = this.calcDistance(b);
		force = G*((myMass*b.myMass)/(r*r));

		return force;
	}

	public double calcForceExertedByX(CelestialBody b) {
		double Xforce = 0.0;
		Xforce = this.calcForceExertedBy(b)*((b.myXPos-myXPos)/this.calcDistance(b));

		return Xforce;
	}
	public double calcForceExertedByY(CelestialBody b) {
		double Yforce = 0.0;
		Yforce = this.calcForceExertedBy(b)*((b.myYPos-myYPos)/this.calcDistance(b));

		return Yforce;
	}

	public double calcNetForceExertedByX(CelestialBody[] bodies) {
		double sum = 0.0;
		for (CelestialBody b : bodies) {
			if (!b.equals(this)){
				sum = sum + calcForceExertedByX(b);
			}
		}
		return sum;
	}

	public double calcNetForceExertedByY(CelestialBody[] bodies) {
		double sum = 0.0;
		for (CelestialBody b : bodies) {
			if (!b.equals(this)){
				sum = sum + calcForceExertedByY(b);
			}
		}
		return sum;
	}

	public void update(double deltaT,
					   double xforce, double yforce) {
		double ax = xforce/myMass;
		double ay = yforce/myMass;
		double nVx = myXVel + (deltaT*ax);
		double nVy = myYVel + (deltaT*ay);
		double nX = myXPos + (deltaT*nVx);
		double nY = myYPos + (deltaT*nVy);

		myXPos = nX;
		myYPos = nY;
		myXVel = nVx;
		myYVel = nVy;

	}

	public void draw() {
		StdDraw.picture(myXPos,myYPos,"images/"+myFileName);
	}
}
