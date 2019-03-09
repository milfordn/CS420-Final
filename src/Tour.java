
public class Tour {
	public int[] cities;
	public int length;
	
	public Tour(int[] cities, int length) {
		this.cities = cities;
		this.length = length;
	}
	
	public Tour(int[] cities) {
		this.cities = cities;
		length = 0;
	}
	
	public int calculateLength(Graph g) {
		length = 0;
		for(int i = 0; i < g.numCities() - 1; i++) {
			length += g.getDistance(cities[i], cities[i+1]);
		}
		length += g.getDistance(cities[0], cities[cities.length - 1]);
		return length;
	}
	
	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append(length);
		b.append('\n');
		
		for(int i = 0; i < cities.length; i++) {
			b.append(cities[i]);
			b.append('\n');
		}
		
		return b.toString();
	}
	
	public String[] printable() {
		String[] toRet = new String[cities.length + 1];
		toRet[0] = length + "";
		for(int i = 0; i < cities.length; i++) {
			toRet[i+1] = cities[i] + "";
		}
		return toRet;
	}
}