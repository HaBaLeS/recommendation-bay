import java.security.SecureRandom;


def buildGuassRandom_12er(): Double = {
	var med = Math.random();
	for(x <-1 to 12){
		med+=Math.abs(Math.random())
	}
	med;
}

def buildGuassRandom_BoxMueller(): Double = {
	val u1 = Math.abs((new SecureRandom()).nextDouble())
	val u2 = Math.abs((new SecureRandom()).nextDouble())
	
	
	Math.cos(u1*2d*Math.PI)* Math.sqrt(-2d*Math.log(u2))
	
	
}

def buildGuassRandom_Polar(): Double = {
	var u = 0d;
	var v = 0d;
	var q = 0d;
	
	do{
		u = 2*Math.abs((new SecureRandom()).nextDouble()) -1
		v = 2*Math.abs((new SecureRandom()).nextDouble()) -1
		
		q = u*u +v*v

	} while( q == 0d || q>1d);
	
	val p = Math.sqrt(-2* Math.log(q) / q)

	u * p
}


for( x <- 0 to 1000000){

	//var z = buildGuassRandom_BoxMueller()
	var z = buildGuassRandom_Polar()
	
	//hard limit to -4 to 4 so we can move our
	//dataset around to get the testdata we want.
	while(z > 4 || z < -4){
		z = buildGuassRandom_Polar()
		//z = buildGuassRandom_BoxMueller()
	}
	z = z+4 //make it positiv vom 0 to 8
	z = z * 1250 //make results form 0 to 10000

	println(z)
}



