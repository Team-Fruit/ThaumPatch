package net.teamfruit.tcpatch.asm;

import java.util.Map;

import javax.annotation.Nullable;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

public class TCPatchCorePlugin implements IFMLLoadingPlugin {
	@Override
	public @Nullable String[] getASMTransformerClass() {
		return new String[] {
				TCPatchTransformer.class.getName()
		};
	}

	@Override
	public @Nullable String getModContainerClass() {
		return null;
	}

	@Override
	public @Nullable String getSetupClass() {
		return null;
	}

	@Override
	public void injectData(final @Nullable Map<String, Object> data) {
	}

	@Override
	public @Nullable String getAccessTransformerClass() {
		return null;
	}
}