package net.teamfruit.tcpatch;

import java.util.Arrays;
import java.util.Collections;

import com.google.common.collect.Lists;

import net.minecraft.potion.Potion;
import net.teamfruit.tcpatch.ConfigBase.ConfigProperty;
import thaumcraft.api.potions.PotionFluxTaint;
import thaumcraft.api.potions.PotionVisExhaust;
import thaumcraft.common.config.Config;
import thaumcraft.common.lib.potions.PotionBlurredVision;
import thaumcraft.common.lib.potions.PotionDeathGaze;
import thaumcraft.common.lib.potions.PotionInfectiousVisExhaust;
import thaumcraft.common.lib.potions.PotionSunScorned;
import thaumcraft.common.lib.potions.PotionThaumarhia;
import thaumcraft.common.lib.potions.PotionUnnaturalHunger;
import thaumcraft.common.lib.potions.PotionWarpWard;
import thaumcraft.common.lib.utils.Utils;

public class TCPotions {
	public static final TCPotions instance = new TCPotions();

	private Configs configs;

	private TCPotions() {
	}

	public void syncConfig() {
		Log.log.info("Hook thaumcraft.common.config.Config.syncConfigurable");
		this.configs = new Configs(Config.config);
	}

	public void initPotions() {
		Log.log.info("Hook thaumcraft.common.config.Config.initPotions");

		if (this.configs==null)
			throw new IllegalStateException(Reference.NAME+" could not load config files.");

		final int potionSize = Potion.potionTypes.length;
		Log.log.info("Found potion array with a size of "+potionSize);

		int offset = potionSize-2;
		final int idPotionFluxTaint = getConfigId(this.configs.idPotionFluxTaint, offset = getNextPotionId(offset+1));
		final int idPotionVisExhaust = getConfigId(this.configs.idPotionVisExhaust, offset = getNextPotionId(offset+1));
		final int idPotionInfectiousVisExhaust = getConfigId(this.configs.idPotionInfectiousVisExhaust, offset = getNextPotionId(offset+1));
		final int idPotionUnnaturalHunger = getConfigId(this.configs.idPotionUnnaturalHunger, offset = getNextPotionId(offset+1));
		final int idPotionWarpWard = getConfigId(this.configs.idPotionWarpWard, offset = getNextPotionId(offset+1));
		final int idPotionDeathGaze = getConfigId(this.configs.idPotionDeathGaze, offset = getNextPotionId(offset+1));
		final int idPotionBlurredVision = getConfigId(this.configs.idPotionBlurredVision, offset = getNextPotionId(offset+1));
		final int idPotionSunScorned = getConfigId(this.configs.idPotionSunScorned, offset = getNextPotionId(offset+1));
		final int idPotionThaumarhia = getConfigId(this.configs.idPotionThaumarhia, offset = getNextPotionId(offset+1));

		final int maxPotionId = Collections.max(Lists.newArrayList(
				idPotionFluxTaint,
				idPotionVisExhaust,
				idPotionInfectiousVisExhaust,
				idPotionUnnaturalHunger,
				idPotionWarpWard,
				idPotionDeathGaze,
				idPotionBlurredVision,
				idPotionSunScorned,
				idPotionThaumarhia));
		final int customPotionSize = maxPotionId+1;

		if (potionSize<customPotionSize) {
			Log.log.info("Extending Potion.potionTypes array to "+customPotionSize);
			final Potion[] potionTypes = Arrays.copyOfRange(Potion.potionTypes, 0, customPotionSize);
			Utils.setPrivateFinalValue(Potion.class, null, potionTypes, "potionTypes", "field_76425_a", "a");
		}

		if (idPotionFluxTaint>=0) {
			Config.potionTaintPoisonID = idPotionFluxTaint;
			PotionFluxTaint.instance = new PotionFluxTaint(idPotionFluxTaint, true, 6697847);
			PotionFluxTaint.init();
			Log.log.info("Initializing PotionFluxTaint with id "+idPotionFluxTaint);
		}

		if (idPotionVisExhaust>=0) {
			Config.potionVisExhaustID = idPotionVisExhaust;
			PotionVisExhaust.instance = new PotionVisExhaust(idPotionVisExhaust, true, 6702199);
			PotionVisExhaust.init();
			Log.log.info("Initializing PotionVisExhaust with id "+idPotionVisExhaust);
		}

		if (idPotionInfectiousVisExhaust>=0) {
			Config.potionInfVisExhaustID = idPotionInfectiousVisExhaust;
			PotionInfectiousVisExhaust.instance = new PotionInfectiousVisExhaust(idPotionInfectiousVisExhaust, true, 6706551);
			PotionInfectiousVisExhaust.init();
			Log.log.info("Initializing PotionInfectiousVisExhaust with id "+idPotionInfectiousVisExhaust);
		}

		if (idPotionUnnaturalHunger>=0) {
			Config.potionUnHungerID = idPotionUnnaturalHunger;
			PotionUnnaturalHunger.instance = new PotionUnnaturalHunger(idPotionUnnaturalHunger, true, 4482611);
			PotionUnnaturalHunger.init();
			Log.log.info("Initializing PotionUnnaturalHunger with id "+idPotionUnnaturalHunger);
		}

		if (idPotionWarpWard>=0) {
			Config.potionWarpWardID = idPotionWarpWard;
			PotionWarpWard.instance = new PotionWarpWard(idPotionWarpWard, false, 14742263);
			PotionWarpWard.init();
			Log.log.info("Initializing PotionWarpWard with id "+idPotionWarpWard);
		}

		if (idPotionDeathGaze>=0) {
			Config.potionDeathGazeID = idPotionDeathGaze;
			PotionDeathGaze.instance = new PotionDeathGaze(idPotionDeathGaze, true, 6702131);
			PotionDeathGaze.init();
			Log.log.info("Initializing PotionDeathGaze with id "+idPotionDeathGaze);
		}

		if (idPotionBlurredVision>=0) {
			Config.potionBlurredID = offset;
			PotionBlurredVision.instance = new PotionBlurredVision(idPotionBlurredVision, true, 8421504);
			PotionBlurredVision.init();
			Log.log.info("Initializing PotionBlurredVision with id "+idPotionBlurredVision);
		}

		if (idPotionSunScorned>=0) {
			Config.potionSunScornedID = idPotionSunScorned;
			PotionSunScorned.instance = new PotionSunScorned(idPotionSunScorned, true, 16308330);
			PotionSunScorned.init();
			Log.log.info("Initializing PotionSunScorned with id "+idPotionSunScorned);
		}

		if (idPotionThaumarhia>=0) {
			Config.potionThaumarhiaID = idPotionThaumarhia;
			PotionThaumarhia.instance = new PotionThaumarhia(idPotionThaumarhia, true, 6702199);
			PotionThaumarhia.init();
			Log.log.info("Initializing PotionThaumarhia with id "+idPotionThaumarhia);
		}
	}

	private int getNextPotionId(int start) {
		if (Potion.potionTypes!=null&&start>0&&start<128&&(start>=Potion.potionTypes.length||Potion.potionTypes[start]==null))
			return start;
		else {
			++start;
			if (start<128)
				start = getNextPotionId(start);
			else
				start = -1;
			return start;
		}
	}

	private int getConfigId(final ConfigProperty<Integer> property, final int defaultId) {
		int id = property.get();
		if (id<0)
			property.set(id = defaultId);
		if (id>=128)
			id = -1;
		return id;
	}
}
