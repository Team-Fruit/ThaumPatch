package net.teamfruit.tcpotionidpatch.asm;

import javax.annotation.Nullable;

import net.minecraft.launchwrapper.IClassTransformer;

public class TCPotionPatchTransformer implements IClassTransformer {
	@Override
	public @Nullable byte[] transform(final @Nullable String name, final @Nullable String transformedName, final @Nullable byte[] bytes) {
		if (bytes==null||name==null||transformedName==null)
			return bytes;

		/*if (transformedName.equals("AAA"))
			return VisitorHelper.apply(bytes, name, new TransformProvider(ClassWriter.COMPUTE_FRAMES) {
				@Override
				public ClassVisitor createVisitor(final String name, final ClassVisitor cv) {
					Log.log.info(String.format("Patching AAA (class: %s)", name));
					return new EntityDummyVisitor(name, cv);
				}
			});*/

		return bytes;
	}
}