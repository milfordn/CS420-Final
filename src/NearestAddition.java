
public class NearestAddition {
	public static Tour NearestAddition(Graph g, int start) {
		LinkedList tour = LinkedList.newList();
		LinkedList available = generateUnusedList(g.numCities());
		int cost = 0;
		int tourLength = 1;
		
		tour.element = start;
		
		while(tourLength < g.numCities()) { //generate the whole tour: theta(n)
			LinkedList nextBestAddition = available;
			LinkedList nextBestFrom = tour.prev;
			int nextBestCost = Integer.MAX_VALUE;
			
			LinkedList i = tour;
			do { //for every element in the tour so far: O(n), average n/2
				LinkedList j = available;
				do { //for every available node: O(n), average n/2
					if(g.getDistance(i.element, j.element) < nextBestCost) {
						nextBestFrom = i;
						nextBestAddition = j;
						nextBestCost = g.getDistance(i.element, j.element);
					}
					j = j.next;
				} while(j != available);
				i = i.next;
			} while(i != tour);
			System.out.println("Adding " + nextBestAddition.element + " from " + nextBestFrom.element + " (" + tourLength * 100 / g.numCities() + "%)");
			cost += addNearestAddition(nextBestFrom, nextBestAddition.element, g);
			available = nextBestAddition.next;
			LinkedList.delete(available.prev);
			tourLength++;
		}
		
		LinkedList t = tour;
		int[] tourList = new int[g.numCities()];
		int i = 0;
		do {
			tourList[i] = t.element;
			t = t.next;
			i++;
		} while(i < g.numCities());
		
		return new Tour(tourList, cost);
	}
	
	public static int addNearestAddition(LinkedList addFrom, int addTo, Graph g) {
		//options: add new node after closest (1), or before closest (2).
		int oldDistance1 = g.getDistance(addFrom.element, addFrom.next.element);
		int newDistance1 = g.getDistance(addFrom.element, addTo) + g.getDistance(addTo, addFrom.next.element);
		int oldDistance2 = g.getDistance(addFrom.prev.element, addFrom.element);
		int newDistance2 = g.getDistance(addFrom.prev.element, addTo) + g.getDistance(addTo, addFrom.element);
		
		if(newDistance1 - oldDistance1 < newDistance2 - oldDistance2) { //adding with option 1 is least
//			System.out.println(addFrom.element + " -> " + addTo + " -> " + addFrom.next.element);
			addFrom.addAfter(addTo);
			return newDistance1 - oldDistance1;
		}
		else {
//			System.out.println(addFrom.prev.element + " -> " + addTo + " -> " + addFrom.element);
			addFrom.addBefore(addTo);
			return newDistance2 - oldDistance2;
		}
	}
	
	public static LinkedList generateUnusedList(int count) {
		LinkedList toRet = new LinkedList(1);
		for(int i = 2; i < count; i++) {
			toRet.addAfter(i);
		}
		return toRet;
	}
}
