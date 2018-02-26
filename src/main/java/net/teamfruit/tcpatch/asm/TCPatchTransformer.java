package net.teamfruit.tcpatch.asm;

import javax.annotation.Nullable;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import net.minecraft.launchwrapper.IClassTransformer;
import net.teamfruit.tcpatch.Log;
import net.teamfruit.tcpatch.asm.lib.VisitorHelper;
import net.teamfruit.tcpatch.asm.lib.VisitorHelper.TransformProvider;

public class TCPatchTransformer implements IClassTransformer {
	@Override
	public @Nullable byte[] transform(final @Nullable String name, final @Nullable String transformedName, final @Nullable byte[] bytes) {
		if (bytes==null||name==null||transformedName==null)
			return bytes;

		if (transformedName.equals("thaumcraft.common.config.Config"))
			return VisitorHelper.apply(bytes, name, new TransformProvider(ClassWriter.COMPUTE_FRAMES) {
				@Override
				public ClassVisitor createVisitor(final String name, final ClassVisitor cv) {
					Log.log.info(String.format("Patching thaumcraft.common.config.Config (class: %s)", name));
					return new TCConfigVisitor(name, cv);
				}
			});

		if (transformedName.equals("thaumcraft.client.gui.ThaumcraftGuiConfig"))
			return VisitorHelper.apply(bytes, name, new TransformProvider(ClassWriter.COMPUTE_FRAMES) {
				@Override
				public ClassVisitor createVisitor(final String name, final ClassVisitor cv) {
					Log.log.info(String.format("Patching thaumcraft.client.gui.ThaumcraftGuiConfig (class: %s)", name));
					return new TCGuiConfigVisitor(name, cv);
				}
			});

		return bytes;
	}
}