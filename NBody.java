/**
 * @author YOUR NAME THE STUDENT IN 201
 *
 * Simulation program for the NBody assignment
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class NBody {

	public static double readRadius(String fname) throws FileNotFoundException  {
		Scanner s = new Scanner(new File(fname));
		double rad = 0.0;

		s.nextInt();
		rad = s.nextDouble();
		s.close();


		return rad;
	}

	public static CelestialBody[] readBodies(String fname) throws FileNotFoundException {

		Scanner s = new Scanner(new File(fname));

		int nb = 0;
		nb = s.nextInt();

		CelestialBody[] bodies = new CelestialBody[nb];
		s.nextDouble();

		for(int k=0; k < nb; k++) {
			double xPos = s.nextDouble();
			double yPos = s.nextDouble();
			double xVel = s.nextDouble();
			double yVel = s.nextDouble();
			double mass = s.nextDouble();
			String gifImage = s.next();

			CelestialBody nextBody = new CelestialBody(xPos, yPos, xVel, yVel, mass, gifImage);
			bodies [k] = nextBody;
		}

		s.close();

		return bodies;
	}
	public static void main(String[] args) throws FileNotFoundException{
		double totalTime = 1000000;
		double dt = 25000.0;

		String fname= "./data/planets.txt";


		if (args.length > 2) {
			totalTime = Double.parseDouble(args[0]);
			dt = Double.parseDouble(args[1]);
			fname = args[2];
		}

		CelestialBody[] bodies = readBodies(fname);
		double radius = readRadius(fname);

		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0,0,"images/starfield.jpg");


		for(double t = 0.0; t < totalTime; t += dt) {

			double[] xForces = new double[bodies.length];
			double[] yForces = new double[bodies.length];

			for(int k=0; k < bodies.length; k++) {

				xForces[k] = bodies[k].calcNetForceExertedByX(bodies);
				yForces[k] = bodies[k].calcNetForceExertedByY(bodies);

			}


			for(int k=0; k < bodies.length; k++){
				bodies[k].update(dt, xForces[k], yForces[k]);
			}

			StdDraw.clear();
			StdDraw.picture(0,0,"images/starfield.jpg");


			for(CelestialBody b : bodies){
				b.draw();
			}
			StdDraw.show();
			StdDraw.pause(10);

		}

		System.out.printf("%d\n", bodies.length);
		System.out.printf("%.2e\n", radius);
		for (int i = 0; i < bodies.length; i++) {
			System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
					bodies[i].getX(), bodies[i].getY(),
					bodies[i].getXVel(), bodies[i].getYVel(),
					bodies[i].getMass(), bodies[i].getName());
		}
	}
}
