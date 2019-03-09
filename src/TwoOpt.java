
public class TwoOpt {
	//Confirmed to work!
	public static Tour twoOpt(Graph g, Tour t) {
		boolean improved = false;
		Tour bestTour = t;
		do {
			improved = false;
			for(int i = 1; i < g.numCities() - 2; i++) {
				for(int k = i+1; k < g.numCities() - 1; k++) {
					int cuti = bestTour.cities[i];
					int cutiPrev = bestTour.cities[i-1];
					int cutk = bestTour.cities[k];
					int cutkPrev = bestTour.cities[k-1];
					
					//delta = new distance - old distance
					int distanceDelta = g.getDistance(cutiPrev, cutkPrev) + g.getDistance(cuti, cutk) - g.getDistance(cutiPrev, cuti) - g.getDistance(cutkPrev, cutk);
					if(distanceDelta < 0) {
						twoOptSwap(bestTour.cities, i, k);
						bestTour.length += distanceDelta;
						improved = true;
					}
				}
			}
		} while(improved);
		
		return bestTour;
	}
	
	//Confirmed to work!
	private static void twoOptSwap(int[] tour, int i, int k) {
		for(int a = i, b = k-1; a < b; a++, b--) { //a = [i..k-1] swaps to indices [k-1..i]
			int temp = tour[a];
			tour[a] = tour[b];
			tour[b] = temp;
		}
	}
}
