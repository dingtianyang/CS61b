public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	private static final double G = 6.67e-11;

	public Planet(double xP, double yP, double xV,
				double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p) {
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p) {
		double d = (this.xxPos - p.xxPos) * (this.xxPos - p.xxPos) + 
			       (this.yyPos - p.yyPos) * (this.yyPos - p.yyPos);
		return Math.sqrt(d);
	}

	public double calcForceExertedBy(Planet p) {
		double f = G * this.mass * p.mass / (this.calcDistance(p) * this.calcDistance(p));
		return f;
	}

	public double calcForceExertedByX(Planet p) {
		double fx = this.calcForceExertedBy(p) * (p.xxPos - this.xxPos) / this.calcDistance(p);
		return fx;
	}

	public double calcForceExertedByY(Planet p) {
		double fy = this.calcForceExertedBy(p) * (p.yyPos - this.yyPos) / this.calcDistance(p);
		return fy;
	}

	public double calcNetForceExertedByX(Planet[] allPlanets) {
		double fx_net = 0;
		for (Planet p : allPlanets) {
			if(!this.equals(p)) {
                fx_net += this.calcForceExertedByX(p);
            }
		}
		return fx_net;
	}

	public double calcNetForceExertedByY(Planet[] allPlanets) {
		double fy_net = 0;
		for (Planet p : allPlanets) {
			if(!this.equals(p)) {
                fy_net += this.calcForceExertedByY(p);
            }
		}
		return fy_net;
	}

	public void update(double dt, double xForce, double yForce) {
		double ax = xForce / this.mass;
		double ay = yForce / this.mass;
		this.xxVel += dt * ax;
		this.yyVel += dt * ay;
		this.xxPos += dt * this.xxVel;
		this.yyPos += dt * this.yyVel;
	}

	public void draw() {
		String imageToDraw = "images/" + this.imgFileName;
		StdDraw.picture(this.xxPos, this.yyPos, imageToDraw);
	}

}