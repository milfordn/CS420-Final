import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	public static void main(String[] argv) {
		String fname;
		if(argv.length < 2) {
			fname = "tsp_example_2.txt";
		} else {
			fname = argv[1];
		}
		
		Graph g; //todo later;
		
		try {
			RandomAccessFile f = new RandomAccessFile(fname, "r");
			int numCities = 0;
			while(f.readLine() != null) {
				numCities++;
			}
			
			g = new Graph(numCities);
			
			f.seek(0);
			String text;
			while((text = f.readLine()) != null && !text.isEmpty()) {
				String[] parts = text.trim().split("\\s+");
				int x = Integer.parseInt(parts[1]);
				int y =  Integer.parseInt(parts[2]);
				g.addCity(Integer.parseInt(parts[0]), x, y);
			}
			f.close();
			
//			Tour t = nearestNeighbor(g, 0);
			Tour t = NearestAddition.NearestAddition(g, 0);
//			t = TwoOpt.twoOpt(g, t);
			Path file = Paths.get(fname + ".tour");
			List<String> lines = new ArrayList<>(Arrays.asList(t.printable()));
			Files.write(file, lines, Charset.forName("UTF-8"));
		} catch (FileNotFoundException e) {
			// TODO fuck java
			e.printStackTrace();
		} catch (IOException e) {
			// TODO AuTo-gENeRaTeD CaTCh blOcK
			e.printStackTrace();
		}
	}
	
	//Confirmed to work!
	public static Tour nearestNeighbor(Graph g, int start) {
		int[] isUsed = new int[g.numCities()];
		int[] tour = new int[g.numCities()];
		int length = 0;
		
		for(int i = 0; i < tour.length; i++) {
			isUsed[i] = 0;
			tour[i] = -1;
		}
		tour[0] = start;
		isUsed[start] = 1;
		
		for(int i = 0; i < tour.length - 1; i++) {
			int nextBestCity = 0;
			int nextBestDistance = Integer.MAX_VALUE;
			for(int j = 0; j < g.numCities(); j++) {
				int distance = 0;
				if(isUsed[j] == 0 && (distance = g.getDistance(tour[i], j)) < nextBestDistance) {
					nextBestCity = j;
					nextBestDistance = distance;
				}
			}
			tour[i+1] = nextBestCity;
			isUsed[nextBestCity] = 1;
			length += nextBestDistance;
		}
		length += g.getDistance(tour[0], tour[g.numCities() - 1]); //back to start
		
		return new Tour(tour, length);
	}
}
