import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BplusTree <K extends Comparable<K>, V>{

	/** 根节点 */
	protected BplusNode<K, V> root;

	/** 阶数，M值 */
	protected int order;

	/** 叶子节点的链表头 */
	protected BplusNode<K, V> head;

	/** 树高*/
	protected int height = 0;

	//The storage which store the String produced by function and show it on the UI
	String out = "";

	long size = 0;

	public BplusNode<K, V> getHead() {
		return head;
	}

	public void setHead(BplusNode<K, V> head) {
		this.head = head;
	}

	public BplusNode<K, V> getRoot() {
		return root;
	}

	public void setRoot(BplusNode<K, V> root) {
		this.root = root;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getHeight() {
		return height;
	}

	public V get(K key) {
		return root.get(key);
	}

	public V remove(K key) {
		return root.remove(key, this);
	}

	public void insertOrUpdate(K key, V value) {
		root.insertOrUpdate(key, value, this);

	}

	public BplusTree(int order) {
		if (order < 3) {
			System.out.print("order must be greater than 2");
			System.exit(0);
		}
		this.order = order;
		root = new BplusNode<K, V>(true, true);
		head = root;
	}

	//Traversal the BPlus_Tree by using the preOrder and print the result on the console
	void preOrderPrint(BplusTree<String,String> bplusTree){
		preOrderPrint(bplusTree.root , 0 , 0);
	}

	//Reload function , recurrently call the function to implement preOrder
	private void preOrderPrint(BplusNode<String,String> bplusNode , int level , int child){
		if(!bplusNode.isLeaf){
			System.out.print("level=" + level + " child=" + child + " /");
			for(int i = 0 ; i < bplusNode.entries.size() ; i++){
				System.out.print(bplusNode.entries.get(i).getKey() + "/");
			}
			System.out.println();
			for(int i = 0 ; i < bplusNode.children.size() ; i++){
				preOrderPrint(bplusNode.children.get(i) , ++level , i);
			}
		}else {
			System.out.print("level=" + level + " child=" + child + " /");
			for(int i = 0 ; i < bplusNode.entries.size() ; i++){
				System.out.print(bplusNode.entries.get(i).getKey()+ " " + bplusNode.entries.get(i).getValue() + "/");
			}
			System.out.println();
		}
	}

	//Same as the preOrder , but put the result in the variable out and use the variable to implement the UI.
	String getTree(BplusTree<K,V> bplusTree){
		out = "";
		recurrent(bplusTree.root , 0 , 0);
		return bplusTree.out;
	}

	//The getTree's recurrent function , same as the reload preOrderPrint function
	private void recurrent(BplusNode<K,V> bplusNode , int level , int child){
		if(!bplusNode.isLeaf){

			out += "level=" + level + " child=" + child + " /";
			for(int i = 0 ; i < bplusNode.entries.size() ; i++){
				out += bplusNode.entries.get(i).getKey() + "/";
			}
			out += "\n";
			for(int i = 0 ; i < bplusNode.children.size() ; i++){
				recurrent(bplusNode.children.get(i) , ++level , i);
			}
		}else {
			if(bplusNode.entries.size() == 0){
				return;
			}
			out += "level=" + level + " child=" + child + " /";
			for(int i = 0 ; i < bplusNode.entries.size() ; i++){
				out += bplusNode.entries.get(i).getKey()+ " " + bplusNode.entries.get(i).getValue() + "/";
			}
			out += "\n";
		}
	}

	//Implement the range search feature
	String rangeSearch(K key1 , K key2){
		return rangeSearch(root , key1 , key2);
	}

	//The rangeSearch's recurrent function
	private String rangeSearch(BplusNode<K,V> x, K key1 , K key2){
		String k1="";
		if(x.isLeaf){
			for(int i = 0 ; i < x.entries.size() ; i++){
				if(x.entries.get(i).getKey().compareTo(key2) > 0){
					return k1;
				}
				if(x.entries.get(i).getKey().compareTo(key1) >= 0 && x.entries.get(i).getKey().compareTo(key2) <= 0){
					k1 += "English: " + x.entries.get(i).getKey() + " Chinese: " + x.entries.get(i).getValue() + "\n";
				}
			}
		}else {
			for(int i = 0 ; i < x.children.size() ; i++){
				k1 += rangeSearch(x.children.get(i) , key1 , key2);
			}
		}
		return k1;
	}

	long getSize(){
		size = 0;
		getSize(root);
		return this.size;
	}

	private void getSize(BplusNode<K,V> bPlusNode){
		if(bPlusNode.isLeaf){
			this.size += bPlusNode.entries.size();
			if(bPlusNode.next != null){
				getSize(bPlusNode.next);
			}
		}else {
			getSize(bPlusNode.children.get(0));
		}
	}
}

