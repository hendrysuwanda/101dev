package mega.trace.transformation;

import java.util.HashMap;

import mega.trace.core.Tracer;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class TransformerClassVisitor extends ClassVisitor implements Opcodes {
	private final Tracer tracer;
	private String currentClass;
	private HashMap<String,String> fieldsigmap=new HashMap<String,String>();
	private String superName;

	public TransformerClassVisitor(final ClassVisitor cv, Tracer tracer) {

		super(Opcodes.ASM4, cv);
		this.tracer=tracer;

	}

	@Override
	public void visit(final int version,final int access,final String name,final String signature,final String superName,final String[] interfaces){
	
		this.currentClass=name;
		this.superName=superName;

		super.visit(version, access, name, signature, superName, interfaces);
	}


	public FieldVisitor visitField(int access, String name, String desc, String signature, Object value){
		fieldsigmap.put(name, desc);
			return	 super.visitField(access, name, desc, signature, value);
	}


	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
		MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);

		mv = new TransformerMethodVisitor(mv,name,superName,tracer,currentClass,fieldsigmap,((access&ACC_STATIC)!=0));
		return mv;
	}

	public void visitEnd(){

		super.visitEnd();
	}
}
