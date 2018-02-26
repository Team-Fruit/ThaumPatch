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

public class TCTileInfusionMatrixVisitor extends ClassVisitor {
	private static class EntireMethodVisitor extends MethodVisitor {
		private final @Nonnull String targetNextIntOwner;
		private final @Nonnull MethodMatcher targetNextIntMethod;
		private final @Nonnull String targetNextIntDesc;

		public EntireMethodVisitor(final @Nullable MethodVisitor mv) {
			super(Opcodes.ASM5, mv);
			this.targetNextIntOwner = MappedType.of("java.util.Random").name();
			this.targetNextIntDesc = DescHelper.toDesc(int.class, int.class);
			this.targetNextIntMethod = new MethodMatcher(this.targetNextIntOwner, this.targetNextIntDesc, RefName.name("nextInt"));
		}

		@Override
		public void visitMethodInsn(final int opcode, final String owner, final String name, final String desc, final boolean itf) {
			if (owner!=null&&name!=null&&desc!=null)
				if (opcode==Opcodes.INVOKEVIRTUAL&&this.targetNextIntOwner.equals(owner)&&this.targetNextIntMethod.match(name, desc)&&this.targetNextIntDesc.equals(desc)) {
					super.visitMethodInsn(Opcodes.INVOKESTATIC, MappedType.of("net.teamfruit.tcpatch.TCHook").name(), "nextInt", DescHelper.toDesc(int.class, "java.util.Random", int.class), false);
					return;
				}
			super.visitMethodInsn(opcode, owner, name, desc, itf);
		}
	}

	public TCTileInfusionMatrixVisitor(final @Nonnull String obfClassName, final @Nonnull ClassVisitor cv) {
		super(Opcodes.ASM5, cv);
	}

	@Override
	public @Nullable MethodVisitor visitMethod(final int access, final @Nullable String name, final @Nullable String desc, final @Nullable String signature, final @Nullable String[] exceptions) {
		final MethodVisitor parent = super.visitMethod(access, name, desc, signature, exceptions);
		if (name==null||desc==null)
			return parent;
		else
			return new EntireMethodVisitor(parent);
	}
}