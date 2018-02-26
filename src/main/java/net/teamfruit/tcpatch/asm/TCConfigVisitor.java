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

public class TCConfigVisitor extends ClassVisitor {
	private static class SyncConfigurableMethodVisitor extends MethodVisitor {
		public SyncConfigurableMethodVisitor(final @Nullable MethodVisitor mv) {
			super(Opcodes.ASM5, mv);
		}

		@Override
		public void visitCode() {
			super.visitMethodInsn(Opcodes.INVOKESTATIC, MappedType.of("net.teamfruit.tcpatch.TCHook").name(), "syncConfigurable", DescHelper.toDesc(void.class, new Object[0]), false);
			super.visitCode();
		}
	}

	private static class InitPotionsMethodVisitor extends MethodVisitor {
		public InitPotionsMethodVisitor(final @Nullable MethodVisitor mv) {
			super(Opcodes.ASM5, mv);
		}

		@Override
		public void visitCode() {
			super.visitMethodInsn(Opcodes.INVOKESTATIC, MappedType.of("net.teamfruit.tcpatch.TCHook").name(), "initPotions", DescHelper.toDesc(void.class, new Object[0]), false);
			super.visitInsn(Opcodes.RETURN);
		}
	}

	private final @Nonnull MethodMatcher syncConfigurableMatcher;
	private final @Nonnull MethodMatcher initPotionsMatcher;

	public TCConfigVisitor(final @Nonnull String obfClassName, final @Nonnull ClassVisitor cv) {
		super(Opcodes.ASM5, cv);
		this.syncConfigurableMatcher = new MethodMatcher(obfClassName, DescHelper.toDesc(void.class, new Object[0]), RefName.name("syncConfigurable"));
		this.initPotionsMatcher = new MethodMatcher(obfClassName, DescHelper.toDesc(int.class, int.class), RefName.name("initPotions"));
	}

	@Override
	public @Nullable MethodVisitor visitMethod(final int access, final @Nullable String name, final @Nullable String desc, final @Nullable String signature, final @Nullable String[] exceptions) {
		final MethodVisitor parent = super.visitMethod(access, name, desc, signature, exceptions);
		if (name==null||desc==null)
			return parent;
		else if (this.syncConfigurableMatcher.match(name, desc))
			return new SyncConfigurableMethodVisitor(parent);
		else if (this.initPotionsMatcher.match(name, desc))
			return new InitPotionsMethodVisitor(parent);
		else
			return parent;
	}
}