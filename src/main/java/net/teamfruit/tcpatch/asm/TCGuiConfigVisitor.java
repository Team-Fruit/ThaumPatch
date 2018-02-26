package net.teamfruit.tcpatch.asm;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import net.teamfruit.tcpatch.asm.lib.DescHelper;
import net.teamfruit.tcpatch.asm.lib.MappedType;
import net.teamfruit.tcpatch.asm.lib.MethodMatcher;
import net.teamfruit.tcpatch.asm.lib.RefName;

public class TCGuiConfigVisitor extends ClassVisitor {
	private static class GetConfigElementsMethodVisitor extends MethodVisitor {
		public GetConfigElementsMethodVisitor(final @Nullable MethodVisitor mv) {
			super(Opcodes.ASM5, mv);
		}

		@Override
		public void visitInsn(final int opcode) {
			if (opcode==Opcodes.ARETURN) {
				super.visitMethodInsn(Opcodes.INVOKESTATIC, MappedType.of("net.teamfruit.tcpatch.TCHook").name(), "getConfigElements", DescHelper.toDesc(void.class, "java.util.List"), false);
				super.visitVarInsn(Opcodes.ALOAD, 0);
			}
			super.visitInsn(opcode);
		}
	}

	private final @Nonnull MethodMatcher getConfigElementsMatcher;

	public TCGuiConfigVisitor(final @Nonnull String obfClassName, final @Nonnull ClassVisitor cv) {
		super(Opcodes.ASM5, cv);
		this.getConfigElementsMatcher = new MethodMatcher(obfClassName, DescHelper.toDesc("java.util.List", new Object[0]), RefName.name("getConfigElements"));
	}

	@Override
	public @Nullable MethodVisitor visitMethod(final int access, final @Nullable String name, final @Nullable String desc, final @Nullable String signature, final @Nullable String[] exceptions) {
		final MethodVisitor parent = super.visitMethod(access, name, desc, signature, exceptions);
		if (name==null||desc==null)
			return parent;
		else if (this.getConfigElementsMatcher.match(name, desc))
			return new GetConfigElementsMethodVisitor(parent);
		else
			return parent;
	}
}