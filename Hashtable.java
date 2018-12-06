import java.util.ArrayList;

public class Hashtable {
	
	private ArrayList<HashNode> buckets;
	private int num_buckets;
	
	public Hashtable() {
		buckets = new ArrayList<>();
		num_buckets = 250000; //2027-314527 (250000 Seems best for me)
		
		for (int x = 0; x < num_buckets; x++) {
			buckets.add(null);
		}
	}
	
	class HashNode { 
		public String key;
		public String value;
		public HashNode next;
		
		public HashNode(String key, String value) {
			this.key = key;
			this.value = value;
		}
	}
	
	public int find_index(String key) {
        int index = Math.abs(key.hashCode() % num_buckets);
        return index;
    }
	
	public void put(String key, String value) {
		int bucketin = find_index(key);
		HashNode head = buckets.get(bucketin);
		
		while (head != null) {
			if (head.key.equals(key)) {
				head.value = value;
				return;
			}
			head = head.next;
		}
		
		head = buckets.get(bucketin);
		HashNode node = new HashNode(key, value);
		node.next = head;
		buckets.set(bucketin, node);
	}
	
	public String get(String key) {
        int bucketin = find_index(key);
        HashNode head = buckets.get(bucketin);
        
        while (head != null) {
        	if (head.key.equals(key)) {
        		return head.value;
        	}
        	
        	head = head.next;
        }
        
        return null;
	}

	public String remove(String key) {
		int bucketin = find_index(key);
		HashNode head = buckets.get(bucketin);
		HashNode prev = null;
		
		while (head != null) {
			if (head.key.equals(key)) {
				break;
			}
			
			prev = head;
			head = head.next;
		}
		
		if (head == null) {
			return null;
		}
		
		if (prev != null) {
			prev.next = head.next;
		} else {
			buckets.set(bucketin, head.next);
		}
		
		return head.value;
		
	}

	public boolean containsKey(String key) {
		return get(key) != null;
	}

	
}
