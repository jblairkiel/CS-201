
public class RBNode {

	
	//Private
	private RBTree RBT;
	private String value;
	private String color;
	private int frequency;
	private int level;
	private RBNode grandparent;
	private RBNode parent;
	private RBNode left;
	private RBNode right;

	public RBTree getRBT() {
		return RBT;
	}
	public void setRBT(RBTree rBT) {
		RBT = rBT;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public RBNode getGrandparent() {
		return grandparent;
	}
	public void setGrandparent(RBNode grandparent) {
		this.grandparent = grandparent;
	}
	public RBNode getParent() {
		return parent;
	}
	public void setParent(RBNode parent) {
		this.parent = parent;
	}
	public RBNode getLeft() {
		return left;
	}
	public void setLeft(RBNode left) {
		this.left = left;
	}
	public RBNode getRight() {
		return right;
	}
	public void setRight(RBNode right) {
		this.right = right;
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	/** Constructor for the Red Black Node
	 * 
	 * @param RBT The tree that the Node is a part of
	 * @param v The value of the new node
	 */
	public RBNode(RBTree RBT, String v){
		this.setRBT(RBT);
		this.setValue(v);
		this.setFrequency(1);
		this.setColor("red");
		this.setLevel(0);
		this.setParent(null);
		this.setLeft(null);
		this.setRight(null);
	}
	public void insertNode(RBTree rbTree, String v) {
		
		if(this.value == null){
			this.setValue(v);
			this.setLevel(1);
		}
		else{
			//If value is equal, increase frequency
			if(this.getValue().equals(v)){
				this.setFrequency(this.getFrequency()+1);
			}
			
			//If value is less than
			else if(this.getValue().compareTo(v)>0){
				if(this.getLeft() != null){
					this.getLeft().insertNode(rbTree, v);
				}
				else{
					this.setLeft(new RBNode(rbTree,v));
					this.getLeft().setLevel(this.getLevel()+1);
					this.getLeft().setParent(this);
					this.getLeft().setGrandparent(this.getParent());
					this.insertionFixUp(this.getLeft());
				}
			}
			
			//If value is greater than
			else if(this.getValue().compareTo(v)<0){
				if(this.getRight() != null){
					this.getRight().insertNode(rbTree, v);
				}
				else{
					this.setRight(new RBNode(rbTree,v));
					this.getRight().setLevel(this.getLevel()+1);
					this.getRight().setParent(this);
					this.getRight().setGrandparent(this.getParent());
					this.insertionFixUp(this.getRight());
				}
			}
		}
	}

	private void insertionFixUp(RBNode node) {
		RBNode x = null;
		x = node;
		boolean flag = true;
		while(flag == true){
			if(x == this.getRBT().getRoot()){
				flag = false;
			}
			if((x.getParent().getColor().equals("black")) && (flag == true)){ 
				flag = false;
			}
			if((uncle(x).getColor().equals("red")) &&(flag == true)){
				x.getParent().setColor("black");
				uncle(x).setColor("black");
				x.getGrandparent().setColor("red");
				x = x.getGrandparent();
			}
				//uncle is black 
			else if(flag==true){
				//if not linear
				if(!isLinear(x)){
					RBNode temp = null;
					temp = x.getParent();
					rotate(x);
					x = temp;
				}
				x.getParent().setColor("black");
				x.getGrandparent().setColor("red");
				rotate(x.getParent());
			}

		}
		this.getRBT().getRoot().setColor("black");
	}
	
	private void rotate(RBNode node){
		RBNode temp = null;
		temp = node.getParent();
		//right rotate
		if(temp.getLeft() == node){
			node.getLeft().setGrandparent(temp.getParent());
			node.getRight().setGrandparent(node);
			node.setGrandparent(temp.getGrandparent());
			node.setParent(temp.getParent());
			temp.setLeft(node.getRight());
			temp.setParent(node);
			temp.setGrandparent(node.getParent());
			node.setRight(temp);
		}
		//left rotate
		else{
			node.getRight().setGrandparent(temp.getParent());
			node.getLeft().setGrandparent(node);
			node.setGrandparent(temp.getGrandparent());
			node.setParent(temp.getParent());
			temp.setRight(node.getLeft());
			temp.setParent(node);
			temp.setGrandparent(node.getParent());
			node.setLeft(temp);
		}
		return;
	}
	
	public void findNode(RBTree binarySearchTree, String v) {
		
		//If less
		if(this.getValue().compareTo(v)>0){
			if(this.getLeft() != null){
				this.getLeft().findNode(binarySearchTree, v);
				return;
			}
			else{
				System.out.printf("\nThe Node '%s' is not found in the tree\n", v);
				return;
			}
		}
		//If greater
		else if(this.getValue().compareTo(v)<0){
			if(this.getRight() != null){
				this.getLeft().findNode(binarySearchTree, v);
				return;
			}
			else{
				System.out.printf("\nThe Node '%s' is not found in the tree\n", v);
				return;
			}
		}
		//If equal
		else if(this.getValue().equals(v)){
			System.out.printf("\nFound '%s'\nFrequency:%d\n",this.getValue(),this.getFrequency());
			return;
		}
		//Supposed to be here but isn't
		else{
			System.out.printf("\nThe Node '%s' is not found in the tree", v);
			return;
		}
	}
	
	private boolean isLinear(RBNode x) {
		if(x.getGrandparent().getLeft() == x.getParent()){
			if(x.getParent().getLeft() == x){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			if(x.getParent().getRight() ==x){
				return true;
			}
			else{
				return false;
			}
		}
	}

	private RBNode uncle(RBNode x){
		if(x.getParent().getLeft() == x){
			return x.getGrandparent().getRight();
		}
		else if(x.getParent().getRight() == x){
			return x.getGrandparent().getLeft();
		}
		return null;
	}
	
}
