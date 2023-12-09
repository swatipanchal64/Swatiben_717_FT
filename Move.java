import java.util.ArrayList;

class SingleObject {
	private String name;

	public SingleObject(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name+ "\n";
	}
}

class Box {
	private int capacityObjects;
	private int boxNumber;

	private ArrayList<Object> items;

	Box(int capacityObjects, int boxNumber) {
		this.capacityObjects = capacityObjects;
		this.boxNumber = boxNumber;
		this.items = new ArrayList<Object>();
	}

	public void addItem(Object s) {
		this.items.add(s);
	}

	@Override
	public String toString() {

		String result = "";

		for (Object o : this.items) {
			result += o;
		}

		return result;
	}

	public int getBoxNumber() {
		return boxNumber;
	}

	public ArrayList<Object> getItems() {
		return items;
	}
}

class Move {
	/* *************************************** */
	// write your code here

	private ArrayList<Box> items;

	public Move(int size) {
		this.items = new ArrayList<Box>(size);
	}

	public void addBox(Box b){
		this.items.add(b);
	}

	public void print() {
		System.out.println("The objects of my move are:");

		for (Box b : this.items) {
			System.out.print(b);
		}
	}

	public int find(String itemName) {
		for (Box box : this.items) {
			if (findItemInBox(box, itemName)) {
				return box.getBoxNumber();
			}
		}
		return -1;
	}

	private boolean findItemInBox(Box box, String itemName) {
		ArrayList<Object> items = box.getItems();
		for (Object item : items) {
			if (item instanceof SingleObject && ((SingleObject) item).getName().equals(itemName)) {
				return true;
			} else if (item instanceof Box && findItemInBox((Box) item, itemName)) {
				return true;
			}
		}
		return false;
	}

	/* *************************************** */

	public static void main(String[] args) {

		// We create a move that will hold 2 main boxes
		Move move = new Move(2);

		/*
		 * We create and then fill 3 boxes
		 * Arguments of the constructor of Box:
		 * argument 1: number of items (simple items/objects or box) that the box can hold
		 * argument 2: box number
		 */

		// box 1 contains scissors
		Box box1 = new Box(1, 1);
		box1.addItem(new SingleObject("scissors"));

		// box 2 contains one book
		Box box2 = new Box(1, 2);
		box2.addItem(new SingleObject("book"));

		// box 3 contains one compass
		// and one box containing one scarf
		Box box3 = new Box(2, 3);
		box3.addItem(new SingleObject("compass"));
		Box box4 = new Box(1, 4);
		box4.addItem(new SingleObject("scarf"));
		box3.addItem(box4);

		// We add the three boxes to the first box of move - see below
		Box box5 = new Box(3, 5);
		box5.addItem(box1);
		box5.addItem(box2);
		box5.addItem(box3);

		// We add one box containing 3 objects to move
		Box box6 = new Box(3, 6);
		box6.addItem(new SingleObject("pencils"));
		box6.addItem(new SingleObject("pens"));
		box6.addItem(new SingleObject("rubber"));

		// We add the two most external boxes to the move
		move.addBox(box5);
		move.addBox(box6);

		// We print all the contents of the move
		move.print();


		// We print the number of the outermost cardboard containing the item "scarf"
		System.out.println("The scarf is in the cardboard number " + move.find("scarf"));

	}
}