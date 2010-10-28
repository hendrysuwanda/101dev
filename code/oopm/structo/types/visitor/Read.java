// (C) 2009 Ralf Laemmel

package structo.types.visitor;

public class Read extends Statement {
	private String id;
	public Read(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public void accept(Visitor v) {
		v.visit(this);
	}
}
