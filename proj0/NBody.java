public class NBody {

	public static double readRadius(String fileName) {
		In in = new In(fileName);
		int number = in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static Planet[] readPlanets(String fileName) {
		In in = new In(fileName);
		int number = in.readInt();
		Planet[] planets = new Planet[number];
		double radius = in.readDouble();
		for (int i = 0; i < number; i++) {
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel =  in.readDouble();
			double mass = in.readDouble();
			String imgFileName = in.readString();
			planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
		}
		return planets;
	}

	public static void main(String[] args) {
		// Collecting All Needed Input
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		Planet[] planets = readPlanets(filename);
		double radius = readRadius(filename);

		// Drawing the Background
		String imageToDraw = "images/starfield.jpg";
		StdDraw.setScale(-radius, radius);
		StdDraw.clear();
		StdDraw.picture(0, 0, imageToDraw);
		
		// Drawing All of the Planets
		for (Planet p : planets) {
			p.draw();
		}

		// Play the theme to 2001: A Space Odyssey
		String audioToPlay = "audio/2001.mid";
		StdAudio.play(audioToPlay);

		// Creating an Animation
		StdDraw.enableDoubleBuffering();
		for (int t = 0; t <= T;) {
			double[] xForces = new double[planets.length];
			double[] yForces = new double[planets.length];
			for (int i = 0; i < planets.length; i++) {
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}

			for (int i = 0; i < planets.length; i++) {
				planets[i].update(dt, xForces[i], yForces[i]);
			}

			StdDraw.picture(0, 0, imageToDraw);
			for (Planet p : planets) {
			p.draw();
			}
			StdDraw.show();
			StdDraw.pause(10);

			t += dt;
		}

		// Printing the Universe
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}

	}

}