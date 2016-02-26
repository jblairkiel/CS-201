
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
					this.getLeft().insertionFixUp();
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
					this.getRight().insertionFixUp();
				}
			}
		}
	}

	private void insertionFixUp() {
		// TODO Auto-generated method stub
		
	}
	
}
