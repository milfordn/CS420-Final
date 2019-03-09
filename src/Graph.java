
public class Graph {
	int[][] distances;
	int[][] cityData;
	
	public Graph(int numCities) {
		distances = new int[numCities][numCities];
		cityData = new int[numCities][2];
		
		for(int i = 0; i < numCities; i++) {
			for(int j = 0; j < numCities; j++) {
				distances[i][j] = 0;
			}
		}
	}
	
	public void addCity(int id, int x, int y) {
		cityData[id][0] = x;
		cityData[id][1] = y;
	}
	
	public int getDistance(int id1, int id2) {
		if(distances[id1][id2] == 0) {
			int dx = cityData[id1][0] - cityData[id2][0];
			int dy = cityData[id1][1] - cityData[id2][1];
			distances[id1][id2] = distances[id2][id1] = Math.round((float)Math.sqrt((float)(dx * dx + dy * dy))); //nearest integer
			return distances[id1][id2];
		}
		else {
			return distances[id1][id2];
		}
	}
	
	public int numCities() {
		return distances.length;
	}
}
